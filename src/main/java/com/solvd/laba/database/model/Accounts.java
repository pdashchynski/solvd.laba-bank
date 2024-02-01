package com.solvd.laba.database.model;

import com.solvd.laba.parsing.xml.jaxb.DateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.sql.Date;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Accounts {

    private int id;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fromDate;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date toDate;
    private int balance;
    private String currency;
    private Clients client;

    public Accounts () {}

    public Accounts (int id, Date fromDate, Date toDate, int balance,
                     String currency, Clients client) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.balance = balance;
        this.currency = currency;
        this.client = client;
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

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", client=" + client +
                '}';
    }
}
