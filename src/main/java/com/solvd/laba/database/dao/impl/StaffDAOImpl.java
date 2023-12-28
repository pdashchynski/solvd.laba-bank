package com.solvd.laba.database.dao.impl;

import com.solvd.laba.database.dao.StaffDAO;
import com.solvd.laba.database.model.Staff;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {

    @Override
    public Staff get(int id) throws SQLException {
        Connection connection = null;
        Staff staff = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM staff WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            int personsId = rs.getInt("persons_idpersons");
            Date dateHired = rs.getDate("date_hired");
            String position = rs.getString("position");
            int salary = rs.getInt("salary");
            int departmentsId = rs.getInt("departments_iddepartments");

            staff = new Staff(gid, personsId, dateHired, position, salary, departmentsId);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return staff;
    }

    @Override
    public List<Staff> getAll() throws SQLException {
        Connection connection = null;
        List<Staff> staffList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM staff";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            int personsId = rs.getInt("persons_idpersons");
            Date dateHired = rs.getDate("date_hired");
            String position = rs.getString("position");
            int salary = rs.getInt("salary");
            int departmentsId = rs.getInt("departments_iddepartments");

            Staff staff = new Staff(gid, personsId, dateHired, position, salary, departmentsId);
            staffList.add(staff);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return staffList;
    }

    @Override
    public void save(Staff staff) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM staff WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, staff.getId());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            //update(staff);
        } else {
            insert(staff);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Staff staff) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO staff (id, persons_idpersons, date_hired, position, salary, departments_iddepartments) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, staff.getId());
        ps.setInt(2, staff.getPersonsId());
        ps.setDate(3, staff.getDateHired());
        ps.setString(4, staff.getPosition());
        ps.setInt(5, staff.getSalary());
        ps.setInt(6, staff.getDepartmentsId());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Staff staff, int id) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM staff WHERE id = ?";
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
