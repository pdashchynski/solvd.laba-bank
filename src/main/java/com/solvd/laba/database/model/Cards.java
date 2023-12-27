package com.solvd.laba.database.model;

import java.time.LocalDate;

public class Cards {

    private int id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String type;
    private int accountsId;

    public Cards () {}

    public Cards (int id, LocalDate fromDate, LocalDate toDate, String type, int accountsId) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.type = type;
        this.accountsId = accountsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(int accountsId) {
        this.accountsId = accountsId;
    }
}
