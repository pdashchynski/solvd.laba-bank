package com.solvd.laba.database.model;

public class Clients {

    private int id;
    private int persons_idpersons;
    private String status;

    public Clients () {}

    public Clients (int id, int persons_idpersons, String status) {
        this.id = id;
        this.persons_idpersons = persons_idpersons;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersons_idpersons() {
        return persons_idpersons;
    }

    public void setPersons_idpersons(int persons_idpersons) {
        this.persons_idpersons = persons_idpersons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
