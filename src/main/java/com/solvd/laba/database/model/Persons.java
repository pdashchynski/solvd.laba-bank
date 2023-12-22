package com.solvd.laba.database.model;

import java.time.LocalDate;

public class Persons {

    private int id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private short age;
    private LocalDate date_of_birth;
    private String gender;
    private int addresses_idaddresses;
    private int passports_idpassports;

    public Persons () {}

    public Persons (int id, String first_name, String middle_name, String last_name, short age,
                    LocalDate date_of_birth, String gender, int addresses_idaddresses,
                    int passports_idpassports) {
        this.id = id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.age = age;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.addresses_idaddresses = addresses_idaddresses;
        this.passports_idpassports = passports_idpassports;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAddresses_idaddresses() {
        return addresses_idaddresses;
    }

    public void setAddresses_idaddresses(int addresses_idaddresses) {
        this.addresses_idaddresses = addresses_idaddresses;
    }

    public int getPassports_idpassports() {
        return passports_idpassports;
    }

    public void setPassports_idpassports(int passports_idpassports) {
        this.passports_idpassports = passports_idpassports;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", date_of_birth=" + date_of_birth +
                ", gender='" + gender + '\'' +
                ", addresses_idaddresses=" + addresses_idaddresses +
                ", passports_idpassports=" + passports_idpassports +
                '}';
    }
}
