package com.solvd.laba.pooling;

import java.sql.DriverManager;

public class Connection {

    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver())
    }
    private String url;
    private int number;

    public Connection(int number, String url) {
        this.number = number;
        this.url = url;
    }

    public String connect() {
        return (this.url + " " + this.number);
    }

    public int getNumber() {
        return number;
    }
}
