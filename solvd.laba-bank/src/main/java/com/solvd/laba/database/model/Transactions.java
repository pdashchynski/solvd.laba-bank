package com.solvd.laba.database.model;

import java.time.LocalDateTime;

public class Transactions {

    private int idtransactions;
    private String type;
    private int amount;
    private String currency;
    private LocalDateTime date_time;
    private int staff_idstaff;
    private int services_idservices;

    public Transactions () {}

    public Transactions (int idtransactions, String type, int amount, String currency,
                        LocalDateTime date_time, int staff_idstaff, int services_idservices) {
        this.idtransactions = idtransactions;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.date_time = date_time;
        this.staff_idstaff = staff_idstaff;
        this.services_idservices = services_idservices;
    }

    public int getIdtransactions() {
        return idtransactions;
    }

    public void setIdtransactions(int idtransactions) {
        this.idtransactions = idtransactions;
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

    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }

    public int getStaff_idstaff() {
        return staff_idstaff;
    }

    public void setStaff_idstaff(int staff_idstaff) {
        this.staff_idstaff = staff_idstaff;
    }

    public int getServices_idservices() {
        return services_idservices;
    }

    public void setServices_idservices(int services_idservices) {
        this.services_idservices = services_idservices;
    }
}
