package com.solvd.laba.database.model;

import java.sql.Time;

public class Departments {

    private int id;
    private Addresses address;
    private Time openTime;
    private Time closeTime;

    public Departments () {}

    public Departments(int id, Addresses address, Time openTime, Time closeTime) {
        this.id = id;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Addresses getAddress() {
        return address;
    }

    public void setAddress(Addresses address) {
        this.address = address;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "id=" + id +
                ", address=" + address +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                '}';
    }
}
