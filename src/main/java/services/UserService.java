package services;

import entity.Order;
import entity.User;
import model.Message;
import provider.UserProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("user")
public class UserService{

    @POST
    @Path("addUser")
    @Produces("application/json")
    @Consumes("application/json")
    public Response addUser(User user){

        UserProvider provider = new UserProvider();
        try {
            provider.addUser(user);
            return Response.status(200).entity(user).build();
        } catch (SQLException e) {
            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }
    }

    @GET
    @Path("getHistory/{nationalId}")
    @Produces("application/json")
    public Response getOrdersHistory(@PathParam("nationalId") String nationalId){

        UserProvider provider = new UserProvider();
        try {
            ArrayList<Order> orders = provider.getOrdersHistory(nationalId);
            return Response.status(200).entity(orders).build();
        } catch (SQLException e) {
            Message m = new Message("SQL Exception", e.getMessage());
            return Response.status(500).entity(m).build();
        } catch (ClassNotFoundException ex) {
            Message m = new Message("Class not found Except", ex.getMessage());
            return Response.status(500).entity(m).build();
        }


    }

}