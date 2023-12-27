package com.solvd.laba.database.model;

import java.time.LocalDateTime;

public class Transactions {

    private int id;
    private String type;
    private int amount;
    private String currency;
    private LocalDateTime dateTime;
    private int staffId;
    private int servicesId;

    public Transactions () {}

    public Transactions (int id, String type, int amount, String currency,
                         LocalDateTime dateTime, int staffId, int servicesId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.dateTime = dateTime;
        this.staffId = staffId;
        this.servicesId = servicesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getServicesId() {
        return servicesId;
    }

    public void setServicesId(int servicesId) {
        this.servicesId = servicesId;
    }
}
