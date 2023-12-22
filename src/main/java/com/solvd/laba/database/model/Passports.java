package com.solvd.laba.database.model;

import java.time.LocalDate;

public class Passports {

    private int id;
    private LocalDate from_date;
    private LocalDate to_date;

    public Passports () {}

    public Passports (int id, LocalDate from_date, LocalDate to_date) {
        this.id = id;
        this.from_date = from_date;
        this.to_date = to_date;
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
}
