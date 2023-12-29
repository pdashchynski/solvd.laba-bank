package com.solvd.laba.database.model;

import java.sql.Date;

public class Passports {

    private int id;
    private Date fromDate;
    private Date toDate;
    private Persons person;

    public Passports () {}

    public Passports (int id, Date fromDate, Date toDate, Persons person) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.person = person;
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

    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }
}
