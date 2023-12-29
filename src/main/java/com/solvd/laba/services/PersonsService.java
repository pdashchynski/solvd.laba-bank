package com.solvd.laba.services;

import com.solvd.laba.database.dao.PersonsDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCPersonsDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisPersonsDAOImpl;
import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.database.model.Persons;

import java.sql.Date;
import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class PersonsService implements GenericCRUDService<Persons> {

    private final PersonsDAO dao;
    private static final AddressesService ADDRESSES_SERVICE = new AddressesService();


    public PersonsService() {
        if (isJdbcOn()) {
            this.dao = new JDBCPersonsDAOImpl();
        } else {
            this.dao = new MyBatisPersonsDAOImpl();
        }
    }

    public Persons create(int id, String firstName, String middleName, String lastName, short age,
                          Date dateOfBirth, String gender, int addressId) {
        Persons person = new Persons();
        person.setId(id);
        person.setFirstName(firstName);
        person.setMiddleName(middleName);
        person.setLastName(lastName);
        person.setAge(age);
        person.setDateOfBirth(dateOfBirth);
        person.setGender(gender);
        Addresses address = ADDRESSES_SERVICE.get(addressId);
        person.setAddress(address);

        return person;
    }

    @Override
    public Persons get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Persons> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Persons person) {
        dao.save(person);
    }

    @Override
    public void insert(Persons person) {
        dao.insert(person);
    }

    @Override
    public void update(Persons person) {
        dao.update(person);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
