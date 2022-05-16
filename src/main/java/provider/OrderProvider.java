package provider;

import db.DbConnection;
import entity.Order;
import entity.Product;
import model.EditOrder;
import model.Inforder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class OrderProvider {

    public void addOrder(Order order) throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String sql = "INSERT INTO ordersA00369206 (creationDate, userID) VALUES ($CREATIONDATE,'$USERID')";
        sql = sql.replace("$CREATIONDATE", Long.toString(new Date().getTime()));
        sql = sql.replace("$USERID", order.getUser());
        conn.runQuery(sql);
        conn.close();
    }

    public Order updateStatus(String info) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        String sql = "UPDATE ordersA00369206 SET payed = 1 WHERE id = $ID";

        long time = System.currentTimeMillis();

        sql = sql.replace("$ID", info);
        conn.runQuery(sql);

        sql = "UPDATE ordersA00369206 SET paymentDate = '$PAY' WHERE id = $ID";

        sql = sql.replace("$ID", info);
        sql = sql.replace("$PAY", Long.toString(time));
        conn.runQuery(sql);

        sql = "SELECT * FROM ordersA00369206 WHERE id = $ID AND paymentDate = $PAYDATE";
        sql = sql.replace("$ID", info);
        sql = sql.replace("$PAYDATE", Long.toString(time));

        //ArrayList<Order> orders = new ArrayList<>();
        Order order = new Order();
        ResultSet results = conn.getData(sql);

        while (results.next()) {

            int id = results.getInt("id");

            int payed = results.getInt("payed");

            String creationDate = results.getString("creationDate");

            String paymentDate = results.getString("paymentDate");

            String userId = results.getString("userID");

            order = new Order(id, Long.parseLong(creationDate), Long.parseLong(paymentDate),payed, userId);
        }
        conn.close();
        return order;
    }

    public void addProductToOrder(EditOrder order) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        String sql = "INSERT INTO orders_productsA00369206 (orderID, productID,amount) VALUES ('$ORDERID','$PRODUCTID',$QUANTITY)";

        sql = sql.replace("$ORDERID", Integer.toString(order.getIdOrder()));
        sql = sql.replace("$PRODUCTID", Integer.toString(order.getIdProduct()));
        sql = sql.replace("$QUANTITY", Integer.toString(order.getAmount()));

        conn.runQuery(sql);
        conn.close();
    }

    public Inforder getInfoFromOrder(String info) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        String sql = "SELECT productsA00369206.id, productsA00369206.name, productsA00369206.price, ordersA00369206.id, orders_productsA00369206.amount FROM (productsA00369206 INNER JOIN orders_productsA00369206 " +
                "ON productsA00369206.id = orders_productsA00369206.productID)INNER JOIN ordersA00369206 ON orders_productsA00369206.orderID = ordersA00369206.id WHERE ordersA00369206.id = $ORDERID";
        sql = sql.replace("$ORDERID", info);
        ArrayList<Product> products = new ArrayList<>();
        int totalPrice = 0;
        int totalAmount = 0;
        ResultSet results = conn.getData(sql);

        while (results.next()) {

            int id = results.getInt("id");

            String name = results.getString("name");

            int price = results.getInt("price");

            int amount = results.getInt("amount");

            totalAmount += amount;
            totalPrice += price*amount;

            Product product = new Product(name, id, price);
            products.add(product);
        }
        Inforder answer = new Inforder(Integer.parseInt(info), products, totalAmount, totalPrice);

        conn.close();
        return answer;
    }

    public void removeProductQuantityFromOrder(EditOrder order) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        String sql = "SELECT * FROM orders_productsA00369206 WHERE orderID = $ORDERID AND productID = $PRODUCTID";
        sql = sql.replace("$ORDERID",  Integer.toString(order.getIdOrder()));
        sql = sql.replace("$PRODUCTID",  Integer.toString(order.getIdProduct()));

        Product product = new Product();
        ResultSet results =  conn.getData(sql);
        int amount=0;

        while(results.next()){
            amount = results.getInt("amount");
        }

        if((amount-order.getAmount())>0) {
            sql="UPDATE orders_productsA00369206 SET amount = $QUANTITY WHERE orderID = $ORDERID AND productID = $PRODUCTID";
            sql= sql.replace("$ORDERID",  Integer.toString(order.getIdProduct()));
            sql = sql.replace("$QUANTITY",    Integer.toString(amount-order.getAmount()));
            sql = sql.replace("$PRODUCTID", Integer.toString(order.getIdProduct()));

            System.out.println(sql);
            conn.runQuery(sql);
            conn.close();
        }else{
            deleteProductFromOrder(order);
            conn.close();
        }
    }

    public void deleteProductFromOrder(EditOrder order) throws SQLException, ClassNotFoundException {

        DbConnection conn = new DbConnection();

        ProductProvider provider = new ProductProvider();

        String sql="DELETE FROM orders_productsA00369206 WHERE orderID = $ORRDERID AND productID = $PRODUCTID";
        sql= sql.replace("$ORRDERID", Integer.toString(order.getIdOrder()));
        sql = sql.replace("$PRODUCTID", Integer.toString(order.getIdProduct()));
        conn.runQuery(sql);
        conn.close();
    }

}