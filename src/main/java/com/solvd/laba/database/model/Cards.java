package com.solvd.laba.database.model;

import java.sql.Date;

public class Cards {

    private int id;
    private Date fromDate;
    private Date toDate;
    private String type;
    private int accountsId;

    public Cards () {}

    public Cards (int id, Date fromDate, Date toDate, String type, int accountsId) {
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
