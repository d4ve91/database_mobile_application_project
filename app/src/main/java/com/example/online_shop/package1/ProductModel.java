package com.example.online_shop.package1;

public class ProductModel {
    private int ID;
    private String productName;
    private double priceProduct;

    public ProductModel(int ID, String productName, double priceProduct) {
        this.ID = ID;
        this.productName = productName;
        this.priceProduct = priceProduct;
    }

    public ProductModel() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    @Override
    public String toString() {
        return "ProduktModel{" +
                "ID_Produkt=" + ID +
                ", Nazwa_Produkt='" + productName + '\'' +
                ", Cena=" + priceProduct +
                '}';
    }
}
