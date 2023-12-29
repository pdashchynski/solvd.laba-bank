package com.solvd.laba.database.model;

import java.sql.Timestamp;

public class Transactions {

    private int id;
    private String type;
    private int amount;
    private String currency;
    private Timestamp dateTime;
    private Staff staff;
    private Services service;

    public Transactions () {}

    public Transactions (int id, String type, int amount, String currency,
                         Timestamp dateTime, Staff staff, Services service) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.dateTime = dateTime;
        this.staff = staff;
        this.service = service;
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

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", dateTime=" + dateTime +
                ", staff=" + staff +
                ", service=" + service +
                '}';
    }
}
