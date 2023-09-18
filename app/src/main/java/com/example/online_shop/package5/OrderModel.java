package com.example.online_shop.package5;

public class OrderModel {
    private int ID;
    private int ID_Orders;
    private int ID_Customers;
    private String orderDate;
    private String orderStatus;

    public OrderModel(int ID, int ID_Orders, int ID_Customers, String orderDate, String orderStatus) {
        this.ID = ID;
        this.ID_Orders = ID_Orders;
        this.ID_Customers = ID_Customers;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public OrderModel(int i, String s, String toString){
    }

    public int getID_Orders() {
        return ID_Orders;
    }

    public void setID_Orders(int ID_Orders) {
        this.ID_Orders = ID_Orders;
    }

    public int getID_Customers() {
        return ID_Customers;
    }

    public void setID_Customers(int ID_Customers) {
        this.ID_Customers = ID_Customers;
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
        return "ZamowieniaModel{" +
                "ID=" + ID +
                ", ID_Zamowienia=" + ID_Orders +
                ", ID_Klienci=" + ID_Customers +
                ", dataZam='" + orderDate + '\'' +
                ", statusZam='" + orderStatus + '\'' +
                '}';
    }
}
