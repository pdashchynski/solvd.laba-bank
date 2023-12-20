package com.solvd.laba.database.model;

import java.time.LocalDate;

public class Passports {

    private int idpassports;
    private LocalDate from_date;
    private LocalDate to_date;

    public Passports () {}

    public Passports (int idpassports, LocalDate from_date, LocalDate to_date) {
        this.idpassports = idpassports;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    public int getIdpassports() {
        return idpassports;
    }

    public void setIdpassports(int idpassports) {
        this.idpassports = idpassports;
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
