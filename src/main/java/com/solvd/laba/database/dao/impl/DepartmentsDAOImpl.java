package com.solvd.laba.database.dao.impl;

import com.solvd.laba.database.dao.DepartmentsDAO;
import com.solvd.laba.database.model.Departments;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsDAOImpl implements DepartmentsDAO {

    @Override
    public Departments get(int id) throws SQLException {
        Connection connection = null;
        Departments department = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM departments WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            int addressesId = rs.getInt("addresses_idaddresses");
            Time openTime = rs.getTime("open_time");
            Time closeTime = rs.getTime("close_time");

            department = new Departments(gid, addressesId, openTime, closeTime);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return department;
    }

    @Override
    public List<Departments> getAll() throws SQLException {
        Connection connection = null;
        List<Departments> departmentsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM departments";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            int addressesId = rs.getInt("addresses_idaddresses");
            Time openTime = rs.getTime("open_time");
            Time closeTime = rs.getTime("close_time");

            Departments department = new Departments(gid, addressesId, openTime, closeTime);
            departmentsList.add(department);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return departmentsList;
    }

    @Override
    public void save(Departments department) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM departments WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, department.getId());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            //update(department);
        } else {
            insert(department);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Departments department) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO departments (id, addresses_idaddresses, open_time, close_time) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, department.getId());
        ps.setInt(2, department.getAddressesId());
        ps.setTime(3, department.getOpenTime());
        ps.setTime(4, department.getCloseTime());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Departments department, int id) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM departments WHERE id = ?";
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
