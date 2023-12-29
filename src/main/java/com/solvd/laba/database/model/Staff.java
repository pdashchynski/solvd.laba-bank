package com.solvd.laba.database.model;

import java.sql.Date;

public class Staff {

    private int id;
    private Date dateHired;
    private String position;
    private int salary;
    private Persons person;
    private Departments department;

    public Staff () {}

    public Staff (int id, Date dateHired,
                  String position, int salary, Persons person, Departments department) {
        this.id = id;
        this.dateHired = dateHired;
        this.position = position;
        this.salary = salary;
        this.person = person;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Persons getPerson() {
        return person;
    }

    public void setPerson(Persons person) {
        this.person = person;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }
}
