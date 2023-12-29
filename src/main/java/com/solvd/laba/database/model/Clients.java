package com.solvd.laba.database.model;

public class Clients {

    private int id;
    private Persons person;
    private String status;

    public Clients () {}

    public Clients (int id, Persons person, String status) {
        this.id = id;
        this.person = person;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", person=" + person +
                ", status='" + status + '\'' +
                '}';
    }
}
