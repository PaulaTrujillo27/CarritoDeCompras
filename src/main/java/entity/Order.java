package entity;

public class Order {

    private int id;
    private long createdDate;
    private long payedDate;
    private int pay;
    private String user;

    public Order(int id, long createdDate, long payedDate, int pay, String user) {
        this.id = id;
        this.createdDate = createdDate;
        this.payedDate = payedDate;
        this.pay = pay;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getPayedDate() {
        return payedDate;
    }

    public int getPay() {
        return pay;
    }

    public String getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public void setPayedDate(long payedDate) {
        this.payedDate = payedDate;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
