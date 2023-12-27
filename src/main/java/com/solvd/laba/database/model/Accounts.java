package com.solvd.laba.database.model;

import java.sql.Date;

public class Accounts {

    private int id;
    private Date fromDate;
    private Date toDate;
    private int balance;
    private String currency;
    private int clientsId;

    public Accounts () {}

    public Accounts (int id, Date fromDate, Date toDate,
                     int balance, String currency, int clientsId) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.balance = balance;
        this.currency = currency;
        this.clientsId = clientsId;
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

    public int getClientsId() {
        return clientsId;
    }

    public void setClientsId(int clientsId) {
        this.clientsId = clientsId;
    }
}
