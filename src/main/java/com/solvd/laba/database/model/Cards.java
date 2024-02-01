package com.solvd.laba.database.model;

import com.solvd.laba.parsing.xml.jaxb.DateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.sql.Date;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cards {

    private int id;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fromDate;
    @XmlJavaTypeAdapter(DateAdapter.class)
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

    @Override
    public String toString() {
        return "Cards{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", type='" + type + '\'' +
                ", account=" + account +
                '}';
    }
}
