package model;

public class EditOrder {

    private int idOrder;
    private int idProduct;
    private int amount;

    public EditOrder(int idOrder, int idProduct, int amount) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.amount = amount;
    }

    public EditOrder() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}