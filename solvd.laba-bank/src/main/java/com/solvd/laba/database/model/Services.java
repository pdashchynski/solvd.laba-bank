package com.solvd.laba.database.model;

public class Services {

    private int idservices;
    private String name;
    private int price;

    public Services () {}

    public Services (int idservices, String name, int price) {
        this.idservices = idservices;
        this.name = name;
        this.price = price;
    }

    public int getIdservices() {
        return idservices;
    }

    public void setIdservices(int idservices) {
        this.idservices = idservices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
