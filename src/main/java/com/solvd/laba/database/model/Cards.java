package com.solvd.laba.database.model;

import java.time.LocalDate;

public class Cards {

    private int id;
    private LocalDate from_date;
    private LocalDate to_date;
    private String type;
    private int accounts_idaccounts;

    public Cards () {}

    public Cards (int id, LocalDate from_date, LocalDate to_date, String type, int accounts_idaccounts) {
        this.id = id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.type = type;
        this.accounts_idaccounts = accounts_idaccounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDate from_date) {
        this.from_date = from_date;
    }

    public LocalDate getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDate to_date) {
        this.to_date = to_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAccounts_idaccounts() {
        return accounts_idaccounts;
    }

    public void setAccounts_idaccounts(int accounts_idaccounts) {
        this.accounts_idaccounts = accounts_idaccounts;
    }
}
