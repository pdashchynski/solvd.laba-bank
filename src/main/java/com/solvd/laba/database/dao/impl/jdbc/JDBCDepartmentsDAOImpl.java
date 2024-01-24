package com.solvd.laba.database.dao.impl.jdbc;

import com.solvd.laba.database.dao.DepartmentsDAO;
import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.database.model.Departments;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDepartmentsDAOImpl implements DepartmentsDAO {

    private final JDBCAddressesDAOImpl jdbcAddressesDAO = new JDBCAddressesDAOImpl();

    @Override
    public Departments get(int id) {
        Connection connection = null;
        Departments department = null;
        try {
            connection = ConnectionPool.getConnection();


            String sql = "SELECT * FROM departments WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Addresses address = jdbcAddressesDAO.get(rs.getInt("addresses_id"));
                Time openTime = rs.getTime("open_time");
                Time closeTime = rs.getTime("close_time");

                department = new Departments(gid, address, openTime, closeTime);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return department;
    }

    @Override
    public List<Departments> getAll() {
        Connection connection = null;
        List<Departments> departmentsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM departments";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Addresses address = jdbcAddressesDAO.get(rs.getInt("addresses_id"));
                Time openTime = rs.getTime("open_time");
                Time closeTime = rs.getTime("close_time");

                Departments department = new Departments(gid, address, openTime, closeTime);
                departmentsList.add(department);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return departmentsList;
    }

    @Override
    public void save(Departments department) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO departments (id, addresses_id, open_time, close_time) " +
                    "VALUES (?, ?, ?, ?) AS new " +
                    "ON DUPLICATE KEY " +
                    "UPDATE departments SET addresses_id = new.addresses_id, " +
                    "open_time = new.open_time, close_time = new.close_time";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, department.getId());
            ps.setInt(2, department.getAddress().getId());
            ps.setTime(3, department.getOpenTime());
            ps.setTime(4, department.getCloseTime());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Departments department) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO departments (id, addresses_id, open_time, close_time) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, department.getId());
            ps.setInt(2, department.getAddress().getId());
            ps.setTime(3, department.getOpenTime());
            ps.setTime(4, department.getCloseTime());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Departments department) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "UPDATE departments SET " +
                    "addresses_id = ?, " +
                    "open_time = ? " +
                    "close_time = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, department.getAddress().getId());
            ps.setTime(2, department.getOpenTime());
            ps.setTime(3, department.getCloseTime());
            ps.setInt(4, department.getId());
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

            String sql = "DELETE FROM departments WHERE id = ?";
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
