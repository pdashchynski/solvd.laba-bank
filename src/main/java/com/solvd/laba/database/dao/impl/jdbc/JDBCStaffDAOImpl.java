package com.solvd.laba.database.dao.impl.jdbc;

import com.solvd.laba.database.dao.StaffDAO;
import com.solvd.laba.database.model.Departments;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.database.model.Staff;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCStaffDAOImpl implements StaffDAO {

    private final JDBCPersonsDAOImpl jdbcPersonsDAO = new JDBCPersonsDAOImpl();
    private final JDBCDepartmentsDAOImpl jdbcDepartmentsDAO = new JDBCDepartmentsDAOImpl();

    @Override
    public Staff get(int id) {
        Connection connection = null;
        Staff staff = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM staff WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Date dateHired = rs.getDate("date_hired");
                String position = rs.getString("position");
                int salary = rs.getInt("salary");
                Persons person = jdbcPersonsDAO.get(rs.getInt("persons_id"));
                Departments department = jdbcDepartmentsDAO.get(rs.getInt("departments_id"));

                staff = new Staff(gid, dateHired, position, salary, person, department);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return staff;
    }

    @Override
    public List<Staff> getAll() {
        Connection connection = null;
        List<Staff> staffList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM staff";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Date dateHired = rs.getDate("date_hired");
                String position = rs.getString("position");
                int salary = rs.getInt("salary");
                Persons person = jdbcPersonsDAO.get(rs.getInt("persons_id"));
                Departments department = jdbcDepartmentsDAO.get(rs.getInt("departments_id"));

                Staff staff = new Staff(gid, dateHired, position, salary, person, department);
                staffList.add(staff);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return staffList;
    }

    @Override
    public void save(Staff staff) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO staff (id, date_hired, position, salary, persons_id, departments_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?) AS new" +
                    "ON DUPLICATE KEY " +
                    "UPDATE staff SET date_hired = new.date_hired, position = new.position " +
                    "salary = new.salary, persons_id = new.persons_id, departments_id = new.departments_id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, staff.getId());
            ps.setDate(2, staff.getDateHired());
            ps.setString(3, staff.getPosition());
            ps.setInt(4, staff.getSalary());
            ps.setInt(5, staff.getPerson().getId());
            ps.setInt(6, staff.getDepartment().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Staff staff) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO staff (id, date_hired, position, salary, persons_id, departments_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, staff.getId());
            ps.setDate(2, staff.getDateHired());
            ps.setString(3, staff.getPosition());
            ps.setInt(4, staff.getSalary());
            ps.setInt(5, staff.getPerson().getId());
            ps.setInt(6, staff.getDepartment().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Staff staff) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "UPDATE staff SET " +
                    "date_hired = ?, " +
                    "position = ? " +
                    "salary = ? " +
                    "persons_id = ? " +
                    "departments_id = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setDate(1, staff.getDateHired());
            ps.setString(2, staff.getPosition());
            ps.setInt(3, staff.getSalary());
            ps.setInt(4, staff.getPerson().getId());
            ps.setInt(5, staff.getDepartment().getId());
            ps.setInt(6, staff.getId());
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

            String sql = "DELETE FROM staff WHERE id = ?";
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
