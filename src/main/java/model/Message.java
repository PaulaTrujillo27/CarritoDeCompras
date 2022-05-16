package model;

public class Message {

    private String msg;
    private String type;

    public Message(String msg, String type) {
        this.msg = msg;
        this.type = type;
    }

    public Message() {
    }

    public String getMsg() {
        return msg;
    }

    public String getType() {
        return type;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setType(String type) {
        this.type = type;
    }
}
