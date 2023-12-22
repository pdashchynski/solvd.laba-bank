package com.solvd.laba.database.model;

import java.time.LocalDate;

public class Staff {

    private int id;
    private int persons_idpersons;
    private LocalDate date_hired;
    private String position;
    private int salary;
    private int departments_iddepartments;

    public Staff () {}

    public Staff (int id, int persons_idpersons, LocalDate date_hired,
                  String position, int salary, int departments_iddepartments) {
        this.id = id;
        this.persons_idpersons = persons_idpersons;
        this.date_hired = date_hired;
        this.position = position;
        this.salary = salary;
        this.departments_iddepartments = departments_iddepartments;
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

    public LocalDate getDate_hired() {
        return date_hired;
    }

    public void setDate_hired(LocalDate date_hired) {
        this.date_hired = date_hired;
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

    public int getDepartments_iddepartments() {
        return departments_iddepartments;
    }

    public void setDepartments_iddepartments(int departments_iddepartments) {
        this.departments_iddepartments = departments_iddepartments;
    }
}
