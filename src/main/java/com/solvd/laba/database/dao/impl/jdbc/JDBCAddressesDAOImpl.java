package com.solvd.laba.database.dao.impl.jdbc;

import com.solvd.laba.database.dao.AddressesDAO;
import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCAddressesDAOImpl implements AddressesDAO {

    @Override
    public Addresses get(int id) {
        Connection connection = null;
        Addresses address = null;
        try {
            connection = ConnectionPool.getConnection();

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
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return address;
    }

    @Override
    public List<Addresses> getAll() {
        Connection connection = null;
        List<Addresses> addressesList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();

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
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return addressesList;
    }

    @Override
    public void save(Addresses address) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO addresses (id, country, city, postal_code) " +
                    "VALUES (?, ?, ?, ?) AS new " +
                    "ON DUPLICATE KEY " +
                    "UPDATE country = new.country, city = new.city, postal_code = new.postal_code";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, address.getId());
            ps.setString(2, address.getCountry());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getPostalCode());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Addresses address) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO addresses (id, country, city, postal_code) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, address.getId());
            ps.setString(2, address.getCountry());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getPostalCode());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Addresses address) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "UPDATE addresses SET country = ?, city = ?, postal_code = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, address.getCountry());
            ps.setString(2, address.getCity());
            ps.setString(3, address.getPostalCode());
            ps.setInt(4, address.getId());
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

            String sql = "DELETE FROM addresses WHERE id = ?";
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