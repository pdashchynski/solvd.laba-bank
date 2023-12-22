package com.solvd.laba.database.model;

public class Addresses {

    private int id;
    private String country;
    private String city;
    private String postal_code;

    public Addresses () {}

    public Addresses (int id, String country, String city, String postal_code) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.postal_code = postal_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
}
