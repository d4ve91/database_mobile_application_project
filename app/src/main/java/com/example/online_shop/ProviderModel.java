package com.example.online_shop;

public class ProviderModel {
    private int ID;
    private String name;
    private long phone_number;
    private String address;

    public ProviderModel(int ID, String name, long phone_number, String address) {
        this.ID = ID;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
    }

    public ProviderModel(){
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DostawcaModel{" +
                "ID_Dostawca=" + ID +
                ", Imie_dostawca='" + name + '\'' +
                ", nr_tel=" + phone_number +
                ", adres='" + address + '\'' +
                '}';
    }
}
