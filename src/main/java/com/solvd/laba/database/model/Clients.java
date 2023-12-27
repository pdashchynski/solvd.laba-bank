package com.solvd.laba.database.model;

public class Clients {

    private int id;
    private int personsId;
    private String status;

    public Clients () {}

    public Clients (int id, int personsId, String status) {
        this.id = id;
        this.personsId = personsId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonsId() {
        return personsId;
    }

    public void setPersonsId(int personsId) {
        this.personsId = personsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
