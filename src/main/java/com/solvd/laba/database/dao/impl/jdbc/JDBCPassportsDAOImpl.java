package com.solvd.laba.database.dao.impl.jdbc;

import com.solvd.laba.database.dao.PassportsDAO;
import com.solvd.laba.database.model.Passports;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPassportsDAOImpl implements PassportsDAO {

    private final JDBCPersonsDAOImpl jdbcPersonsDAO = new JDBCPersonsDAOImpl();

    @Override
    public Passports get(int id) {
        Connection connection = null;
        Passports passport = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM passports WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Date fromDate = rs.getDate("from_date");
                Date toDate = rs.getDate("to_date");
                Persons person = jdbcPersonsDAO.get(rs.getInt("persons_id"));

                passport = new Passports(gid, fromDate, toDate, person);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return passport;
    }

    @Override
    public List<Passports> getAll() {
        Connection connection = null;
        List<Passports> passportsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM passports";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Date fromDate = rs.getDate("from_date");
                Date toDate = rs.getDate("to_date");
                Persons person = jdbcPersonsDAO.get(rs.getInt("persons_id"));

                Passports passport = new Passports(gid, fromDate, toDate, person);
                passportsList.add(passport);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return passportsList;
    }

    @Override
    public void save(Passports passport) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO passports (id, from_date, to_date, persons_id) " +
                    "VALUES (?, ?, ?, ?) AS new" +
                    "ON DUPLICATE KEY " +
                    "UPDATE passports SET from_date = new.from_date, " +
                    "to_date = new.to_date, persons_id = new.persons_id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, passport.getId());
            ps.setDate(2, passport.getFromDate());
            ps.setDate(3, passport.getToDate());
            ps.setInt(4, passport.getPerson().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Passports passport) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO passports (id, from_date, to_date, persons_id) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, passport.getId());
            ps.setDate(2, passport.getFromDate());
            ps.setDate(3, passport.getToDate());
            ps.setInt(4, passport.getPerson().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Passports passport) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "UPDATE passports SET " +
                    "from_date = ?, " +
                    "to_date = ? " +
                    "persons_id = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setDate(1, passport.getFromDate());
            ps.setDate(2, passport.getToDate());
            ps.setInt(3, passport.getPerson().getId());
            ps.setInt(4, passport.getId());
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

            String sql = "DELETE FROM passports WHERE id = ?";
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
