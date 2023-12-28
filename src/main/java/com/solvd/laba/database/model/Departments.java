package com.solvd.laba.database.model;

import java.sql.Time;

public class Departments {

    private int id;
    private int addressesId;
    private Time openTime;
    private Time closeTime;

    public Departments () {}

    public Departments(int id, int addressesId, Time openTime, Time closeTime) {
        this.id = id;
        this.addressesId = addressesId;
        this.openTime = openTime;
        this.closeTime = closeTime;
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
}
