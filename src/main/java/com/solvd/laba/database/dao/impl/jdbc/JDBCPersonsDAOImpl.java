package com.solvd.laba.database.dao.impl.jdbc;

import com.solvd.laba.database.dao.PersonsDAO;
import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPersonsDAOImpl implements PersonsDAO {

    private final JDBCAddressesDAOImpl jdbcAddressesDAO = new JDBCAddressesDAOImpl();

    @Override
    public Persons get(int id) {
        Connection connection = null;
        Persons person = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM persons WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String middle_name = rs.getString("middle_name");
                String last_name = rs.getString("last_name");
                short age = rs.getShort("age");
                Date date_of_birth = rs.getDate("date_of_birth");
                String gender = rs.getString("gender");
                Addresses address = jdbcAddressesDAO.get(rs.getInt("addresses_id"));

                person = new Persons(gid, first_name, middle_name, last_name, age,
                        date_of_birth, gender, address);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return person;
    }

    @Override
    public List<Persons> getAll() {
        Connection connection = null;
        List<Persons> personsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM persons";
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
                Addresses address = jdbcAddressesDAO.get(rs.getInt("addresses_id"));

                Persons person = new Persons(gid, first_name, middle_name, last_name, age,
                        date_of_birth, gender, address);
                personsList.add(person);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return personsList;
    }

    @Override
    public void save(Persons person) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO persons (id, first_name, middle_name, last_name, age, date_of_birth, gender, addresses_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?) AS new " +
                    "ON DUPLICATE KEY " +
                    "UPDATE first_name = new.first_name, " +
                    "middle_name = new.middle_name, last_name = new.last_name, age = new.age, " +
                    "date_of_birth = new.date_of_birth, gender = new.gender, addresses_id = new.addresses_id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, person.getId());
            ps.setString(2, person.getFirstName());
            ps.setString(3, person.getMiddleName());
            ps.setString(4, person.getLastName());
            ps.setShort(5, person.getAge());
            ps.setDate(6, person.getDateOfBirth());
            ps.setString(7, person.getGender());
            ps.setInt(8, person.getAddress().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Persons person) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO persons (id, first_name, middle_name, last_name, age, date_of_birth, gender, addresses_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, person.getId());
            ps.setString(2, person.getFirstName());
            ps.setString(3, person.getMiddleName());
            ps.setString(4, person.getLastName());
            ps.setShort(5, person.getAge());
            ps.setDate(6, person.getDateOfBirth());
            ps.setString(7, person.getGender());
            ps.setInt(8, person.getAddress().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Persons person) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "UPDATE persons SET " +
                    "first_name = ?, " +
                    "middle_name = ? " +
                    "last_name = ? " +
                    "age = ? " +
                    "date_of_birth = ? " +
                    "gender = ? " +
                    "addresses_id = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getMiddleName());
            ps.setString(3, person.getLastName());
            ps.setShort(4, person.getAge());
            ps.setDate(5, person.getDateOfBirth());
            ps.setString(6, person.getGender());
            ps.setInt(7, person.getAddress().getId());
            ps.setInt(8, person.getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "DELETE FROM persons WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }
}
