package com.solvd.laba.database.dao.impl;

import com.solvd.laba.database.dao.GenericCRUD;
import com.solvd.laba.database.dao.PersonsDAO;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonsDAOImpl implements GenericCRUD<Persons>, PersonsDAO {

    @Override
    public Persons get(long id) throws SQLException {
        Connection connection = null;
        Persons person = null;
        String sql = "SELECT * FROM persons WHERE id =" + id;

        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String middle_name = rs.getString("middle_name");
            String last_name = rs.getString("last_name");
            short age = rs.getShort("age");
            Date date_of_birth = rs.getDate("date_of_birth");
            String gender = rs.getString("gender");
            int addresses_idaddresses = rs.getInt("id");
            int passports_idpassports = rs.getInt("id");

            person = new Persons(gid, first_name, middle_name, last_name, age,
                    date_of_birth, gender, addresses_idaddresses, passports_idpassports);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return person;
    }

    @Override
    public List<Persons> getAll() throws SQLException {
        Connection connection = null;
        List<Persons> personsList = new ArrayList<>();
        String sql = "SELECT * FROM persons";

        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String middle_name = rs.getString("middle_name");
            String last_name = rs.getString("last_name");
            short age = rs.getShort("age");
            Date date_of_birth = rs.getDate("date_of_birth");
            String gender = rs.getString("gender");
            int addresses_idaddresses = rs.getInt("id");
            int passports_idpassports = rs.getInt("id");

            Persons person = new Persons(id, first_name, middle_name, last_name, age,
                    date_of_birth, gender, addresses_idaddresses, passports_idpassports);

            personsList.add(person);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return personsList;
    }

    @Override
    public void save(Persons persons) {

    }

    @Override
    public void insert(Persons persons) {

    }

    @Override
    public void update(Persons persons, String[] params) {

    }

    @Override
    public void delete(Persons persons) {

    }
}
