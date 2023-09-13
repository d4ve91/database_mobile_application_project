package com.example.online_shop;

public class DetailsProductModel {
    private int ID;
    private String category;
    private String producer;
    private String added_date;

    public DetailsProductModel(int ID, String category, String producer, String added_date) {
        this.ID = ID;
        this.category = category;
        this.producer = producer;
        this.added_date = added_date;
    }

    public DetailsProductModel(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getAdded_date() {
        return added_date;
    }

    public void setAdded_date(String added_date) {
        this.added_date = added_date;
    }

    @Override
    public String toString() {
        return "SzczegolyProduktuModel{" +
                "ID_Dostawca=" + ID +
                ", kategoria='" + category + '\'' +
                ", producent='" + producer + '\'' +
                ", data_dodania=" + added_date +
                '}';
    }
}
