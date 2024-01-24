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
    private Addresses address;

    public Persons () {}

    public Persons (int id, String firstName, String middleName, String lastName, short age,
                    Date dateOfBirth, String gender, Addresses address) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
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

    public Addresses getAddress() {
        return address;
    }

    public void setAddress(Addresses address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", address=" + address +
                '}';
    }

    private Persons(PersonsBuilder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.dateOfBirth = builder.dateOfBirth;
        this.gender = builder.gender;
        this.address = builder.address;
    }

    public static class PersonsBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private short age;
        private Date dateOfBirth;
        private String gender;
        private Addresses address;
        private String middleName;

        public PersonsBuilder (int id, String firstName, String lastName, short age,
                        Date dateOfBirth, String gender, Addresses address) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.dateOfBirth = dateOfBirth;
            this.gender = gender;
            this.address = address;
        }

        public PersonsBuilder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Persons build() {
            return new Persons(this);
        }
    }
}
