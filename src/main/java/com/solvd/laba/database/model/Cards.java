package com.solvd.laba.database.model;

import java.sql.Date;

public class Cards {

    private int id;
    private Date fromDate;
    private Date toDate;
    private String type;
    private Accounts account;

    public Cards () {}

    public Cards (int id, Date fromDate, Date toDate, String type, Accounts account) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.type = type;
        this.account = account;
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

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }
}
