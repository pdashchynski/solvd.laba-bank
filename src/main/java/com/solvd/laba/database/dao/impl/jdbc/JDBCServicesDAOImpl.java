package com.solvd.laba.database.dao.impl.jdbc;

import com.solvd.laba.database.dao.ServicesDAO;
import com.solvd.laba.database.model.Services;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCServicesDAOImpl implements ServicesDAO {

    @Override
    public Services get(int id) {
        Connection connection = null;
        Services service = null;
        try {
            connection = ConnectionPool.getConnection();

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
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return service;
    }

    @Override
    public List<Services> getAll() {
        Connection connection = null;
        List<Services> servicesList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();

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
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return servicesList;
    }

    @Override
    public void save(Services service) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO services (id, name, price) " +
                    "VALUES (?, ?, ?) AS new " +
                    "ON DUPLICATE KEY " +
                    "UPDATE services SET name = new.name, price = new.price";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, service.getId());
            ps.setString(2, service.getName());
            ps.setInt(3, service.getPrice());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Services service) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
            String sql = "INSERT INTO services (id, name, price) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, service.getId());
            ps.setString(2, service.getName());
            ps.setInt(3, service.getPrice());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Services service) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "UPDATE services SET " +
                    "name = ?, " +
                    "price = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, service.getName());
            ps.setInt(2, service.getPrice());
            ps.setInt(3, service.getId());
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

            String sql = "DELETE FROM services WHERE id = ?";
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
