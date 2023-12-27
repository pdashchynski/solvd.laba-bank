package com.solvd.laba.database.model;

public class Departments {

    private int id;
    private int addressesId;
    private String schedule;

    public Departments () {}

    public Departments (int id, int addressesId, String schedule) {
        this.id = id;
        this.addressesId = addressesId;
        this.schedule = schedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddressesId() {
        return addressesId;
    }

    public void setAddressesId(int addressesId) {
        this.addressesId = addressesId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
