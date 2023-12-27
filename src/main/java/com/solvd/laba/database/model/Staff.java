package com.solvd.laba.database.model;

import java.sql.Date;

public class Staff {

    private int id;
    private int personsId;
    private Date dateHired;
    private String position;
    private int salary;
    private int departmentsId;

    public Staff () {}

    public Staff (int id, int personsId, Date dateHired,
                  String position, int salary, int departmentsId) {
        this.id = id;
        this.personsId = personsId;
        this.dateHired = dateHired;
        this.position = position;
        this.salary = salary;
        this.departmentsId = departmentsId;
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

    public int getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(int departmentsId) {
        this.departmentsId = departmentsId;
    }
}
