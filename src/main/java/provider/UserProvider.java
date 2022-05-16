package provider;

import db.DbConnection;
import entity.Order;
import entity.Product;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserProvider {

    public void addUser(User user) throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String sql="INSERT INTO usersA00365285 (natID) VALUES ('$USERID')";
        sql= sql.replace("$USERID",user.getId());
        conn.runQuery(sql);
        conn.close();
    }

    public ArrayList<Order> getOrdersHistory(String natID) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        String sql = "SELECT ordersA00365285.id, ordersA00365285.creationDate, ordersA00365285.paymentDate FROM ordersA00365285 WHERE ordersA00365285.userID = '$ORDERID'" ;

        sql = sql.replace("$ORDERID",natID);
        ResultSet results =  conn.getData(sql);
        ArrayList<Order> orders = new ArrayList<>();

        while(results.next()){
            int payed=0;
            int id = results.getInt("id");

            String creationDate  = results.getString("creationDate");

            String paymentDate = results.getString("paymentDate");

            if(!paymentDate.equals("0")){
                payed=1;
            }
            Order order = new Order(id, Long.parseLong(creationDate), Long.parseLong(paymentDate),payed, natID);
            orders.add(order);
        }
        return orders;

    }
}

