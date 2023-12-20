package com.solvd.laba.database.model;

import java.util.Date;

public class Clients {

    private int idclients;
    private int persons_idpersons;
    private String status;

    public Clients () {}

    public Clients (int idclients, int persons_idpersons, String status) {
        this.idclients = idclients;
        this.persons_idpersons = persons_idpersons;
        this.status = status;
    }

    public int getIdclients() {
        return idclients;
    }

    public void setIdclients(int idclients) {
        this.idclients = idclients;
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
