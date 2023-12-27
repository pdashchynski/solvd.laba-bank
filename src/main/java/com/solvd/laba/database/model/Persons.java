package com.solvd.laba.database.model;

import java.sql.Date;

public class Persons {

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private short age;
    private Date dateOfBirth;
    private String gender;
    private int addressesId;
    private int passportsId;

    public Persons () {}

    public Persons (int id, String firstName, String middleName, String lastName, short age,
                    Date dateOfBirth, String gender, int addressesId,
                    int passportsId) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.addressesId = addressesId;
        this.passportsId = passportsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAddressesId() {
        return addressesId;
    }

    public void setAddressesId(int addressesId) {
        this.addressesId = addressesId;
    }

    public int getPassportsId() {
        return passportsId;
    }

    public void setPassportsId(int passportsId) {
        this.passportsId = passportsId;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", middle_name='" + middleName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", age=" + age +
                ", date_of_birth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", addressesId=" + addressesId +
                ", passportsId=" + passportsId +
                '}';
    }
}
