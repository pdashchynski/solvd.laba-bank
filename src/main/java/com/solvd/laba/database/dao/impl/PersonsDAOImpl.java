package com.solvd.laba.database.dao.impl;

import com.solvd.laba.database.dao.PersonsDAO;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonsDAOImpl implements PersonsDAO {

    @Override
    public Persons get(int id) throws SQLException {
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
            int addressesId = rs.getInt("addresses_idaddresses");
            int passportsId = rs.getInt("passports_idpassports");

            person = new Persons(gid, first_name, middle_name, last_name, age,
                    date_of_birth, gender, addressesId, passportsId);
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
            int addressesId = rs.getInt("addresses_idaddresses");
            int passportsId = rs.getInt("passports_idpassports");

            Persons person = new Persons(id, first_name, middle_name, last_name, age,
                    date_of_birth, gender, addressesId, passportsId);

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
    public void save(Persons person) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM passports WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, person.getId());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            //update(person);
        } else {
            insert(person);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Persons person) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO persons (id, first_name, middle_name, last_name, age, date_of_birth, gender, addresses_idaddresses, passports_idpassports) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, person.getId());
        ps.setString(2, person.getFirstName());
        ps.setString(3, person.getMiddleName());
        ps.setString(4, person.getLastName());
        ps.setShort(5, person.getAge());
        ps.setDate(6, person.getDateOfBirth());
        ps.setString(7, person.getGender());
        ps.setInt(8, person.getAddressesId());
        ps.setInt(9, person.getPassportsId());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Persons person, int id) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM persons WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, id);
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
