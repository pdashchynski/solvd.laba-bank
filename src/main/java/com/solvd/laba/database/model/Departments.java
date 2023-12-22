package com.solvd.laba.database.model;

public class Departments {

    private int id;
    private int addresses_idaddresses;
    private String schedule;

    public Departments () {}

    public Departments (int id, int addresses_idaddresses, String schedule) {
        this.id = id;
        this.addresses_idaddresses = addresses_idaddresses;
        this.schedule = schedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddresses_idaddresses() {
        return addresses_idaddresses;
    }

    public void setAddresses_idaddresses(int addresses_idaddresses) {
        this.addresses_idaddresses = addresses_idaddresses;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
