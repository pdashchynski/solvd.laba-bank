package com.solvd.laba.database.model;

import java.time.LocalDate;

public class Accounts {

    private int id;
    private LocalDate from_date;
    private LocalDate to_date;
    private int balance;
    private String currency;
    private int clients_idclients;

    public Accounts () {}

    public Accounts (int id, LocalDate from_date, LocalDate to_date,
                     int balance, String currency, int clients_idclients) {
        this.id = id;
        this.from_date = from_date;
        this.to_date = to_date;
        this.balance = balance;
        this.currency = currency;
        this.clients_idclients = clients_idclients;
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

    public int getClients_idclients() {
        return clients_idclients;
    }

    public void setClients_idclients(int clients_idclients) {
        this.clients_idclients = clients_idclients;
    }
}
