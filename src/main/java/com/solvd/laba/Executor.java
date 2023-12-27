package com.solvd.laba;

import com.solvd.laba.database.dao.PersonsDAO;
import com.solvd.laba.database.dao.impl.PersonsDAOImpl;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Executor {

    public static void main(String[] args) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PersonsDAOImpl personsDAO = new PersonsDAOImpl();
        List<Persons> personsList = null;
        try {
            personsList = personsDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Persons person : personsList) {
            System.out.println(person.toString());
        }
        Persons person = null;
        try {
            person = personsDAO.get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(person.toString());
    }
}
