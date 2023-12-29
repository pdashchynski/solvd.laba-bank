package com.solvd.laba.database.model;

import java.sql.Date;

public class Accounts {

    private int id;
    private Date fromDate;
    private Date toDate;
    private int balance;
    private String currency;
    private Clients client;

    public Accounts () {}

    public Accounts (int id, Date fromDate, Date toDate, int balance,
                     String currency, Clients client) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.balance = balance;
        this.currency = currency;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }
}
