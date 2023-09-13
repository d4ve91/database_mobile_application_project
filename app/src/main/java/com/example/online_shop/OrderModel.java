package com.example.online_shop;

public class OrderModel {
    private int ID;
    private String orderDate;
    private String orderStatus;

    public OrderModel(int ID, String orderDate, String orderStatus) {
        this.ID = ID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public OrderModel(){
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "ID=" + ID +
                ", orderDate='" + orderDate + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
