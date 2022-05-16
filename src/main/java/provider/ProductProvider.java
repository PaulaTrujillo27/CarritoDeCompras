package provider;

import db.DbConnection;
import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductProvider {

    public void addProduct(Product product) throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();

        String sql="INSERT INTO productsA00365285(name,price) VALUES ('$NAME','$PRICE')";
        sql= sql.replace("$NAME",product.getName());
        sql = sql.replace("$PRICE", Double.toString(product.getPrice()));
        conn.runQuery(sql);
        conn.close();
    }
    public int searchForProductId(String name) throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();

        String sql="SELECT id FROM productsA00365285 WHERE name = '$NAME'";
        sql = sql.replace("$NAME", name);
        ResultSet results =  conn.getData(sql);
        int id = 0;
        while(results.next()) {
            id= results.getInt("id");
        }
        conn.close();
        return id;
    }
}