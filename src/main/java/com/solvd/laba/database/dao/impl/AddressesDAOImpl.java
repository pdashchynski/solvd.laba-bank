package com.solvd.laba.database.dao.impl;

import com.solvd.laba.database.dao.AddressesDAO;
import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressesDAOImpl implements AddressesDAO {

    @Override
    public Addresses get(int id) throws SQLException {
        Connection connection = null;
        Addresses address = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM addresses WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            String country = rs.getString("country");
            String city = rs.getString("city");
            String postalCode = rs.getString("postal_code");

            address = new Addresses(gid, country, city, postalCode);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return address;
    }

    @Override
    public List<Addresses> getAll() throws SQLException {
        Connection connection = null;
        List<Addresses> addressesList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM addresses";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            String country = rs.getString("country");
            String city = rs.getString("city");
            String postalCode = rs.getString("postal_code");

            Addresses address = new Addresses(gid, country, city, postalCode);
            addressesList.add(address);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return addressesList;
    }

    @Override
    public void save(Addresses address) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM addresses WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, address.getId());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            //update(address);
        } else {
            insert(address);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Addresses address) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO addresses (id, country, city, postal_code) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, address.getId());
        ps.setString(2, address.getCountry());
        ps.setString(3, address.getCity());
        ps.setString(4, address.getPostalCode());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Addresses address, int id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE addresses set " +
                "id = ?, " +
                "country = ?, " +
                "city = ?, " +
                "postal_code = ?) ";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, address.getId());
        ps.setString(2, address.getCountry());
        ps.setString(3, address.getCity());
        ps.setString(4, address.getPostalCode());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM addresses WHERE id = ?";
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