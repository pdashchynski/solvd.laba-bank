package com.solvd.laba.database.dao.impl;

import com.solvd.laba.database.dao.ServicesDAO;
import com.solvd.laba.database.model.Services;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicesDAOImpl implements ServicesDAO {

    @Override
    public Services get(int id) throws SQLException {
        Connection connection = null;
        Services service = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM services WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");

            service = new Services(gid, name, price);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return service;
    }

    @Override
    public List<Services> getAll() throws SQLException {
        Connection connection = null;
        List<Services> servicesList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM services";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");

            Services service = new Services(gid, name, price);
            servicesList.add(service);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return servicesList;
    }

    @Override
    public void save(Services service) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM services WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, service.getId());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            //update(service);
        } else {
            insert(service);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Services service) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO services (id, name, price) " +
                "VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, service.getId());
        ps.setString(2, service.getName());
        ps.setInt(3, service.getPrice());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Services service, int id) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM services WHERE id = ?";
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
