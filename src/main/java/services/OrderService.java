package services;

import entity.Order;
import model.EditOrder;
import model.Inforder;
import model.Message;

import provider.OrderProvider;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("order")
public class OrderService {

    @POST
    @Path("addOrder")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addOrder(Order order){
        OrderProvider provider = new OrderProvider();
        try {
            provider.addOrder(order);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {
            Message m = new Message("Usuario no encontrado", "El ID de usuario no corresponde a un usuario registrado anteriormente");
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }
    }

    @PUT
    @Path("addProductToOrder")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addProductToOrder(EditOrder order){

        OrderProvider provider = new OrderProvider();
        try {
            provider.addProductToOrder(order);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {
            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }
    }

    @PUT
    @Path("updatePayStatus/{info}")
    @Produces("application/json")
    public Response updateStatus(@PathParam("info") String info){

        OrderProvider provider = new OrderProvider();
        try {
            Order order = provider.updateStatus(info);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {
            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }
    }
    @GET
    @Path("getInfoOrder/{info}")
    @Produces("application/json")
    public Response getInfoOrder(@PathParam("info") String info){

        OrderProvider provider = new OrderProvider();
        try {
            Inforder data = provider.getInfoFromOrder(info);
            return Response.status(200).entity(data).build();
        } catch (SQLException e) {
            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }
    }
    @PUT
    @Path("removeProductQuantityFromOrder")
    @Produces("application/json")
    @Consumes("application/json")
    public Response removeProductFromOrder(EditOrder order){

        OrderProvider provider = new OrderProvider();
        try {
            provider.removeProductQuantityFromOrder(order);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {
            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }
    }

    @DELETE
    @Path("deleteProductFromOrder")
    @Produces("application/json")
    @Consumes("application/json")
    public Response deleteProductFromOrder(EditOrder order){

        OrderProvider provider = new OrderProvider();
        try {
            provider.deleteProductFromOrder(order);
            return Response.status(200).entity(order).build();
        } catch (SQLException e) {

            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }

    }

}