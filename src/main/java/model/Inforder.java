package model;

import entity.Order;
import entity.Product;

import java.util.ArrayList;

public class Inforder {

    private int idOrder;
    private ArrayList <Product> products;

    private int amount;

    private int price;

    public Inforder(int idOrder, ArrayList<Product> products, int amount, int price) {
        this.idOrder = idOrder;
        this.products = products;
        this.amount = amount;
        this.price = price;
    }

    public Inforder() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}