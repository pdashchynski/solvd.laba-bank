package com.solvd.laba.database.model;

public class Departments {

    private int iddepartments;
    private int addresses_idaddresses;
    private String schedule;

    public Departments () {}

    public Departments (int iddepartments, int addresses_idaddresses, String schedule) {
        this.iddepartments = iddepartments;
        this.addresses_idaddresses = addresses_idaddresses;
        this.schedule = schedule;
    }

    public int getIddepartments() {
        return iddepartments;
    }

    public void setIddepartments(int iddepartments) {
        this.iddepartments = iddepartments;
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
