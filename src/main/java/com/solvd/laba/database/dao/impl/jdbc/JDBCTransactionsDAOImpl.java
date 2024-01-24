package com.solvd.laba.database.dao.impl.jdbc;

import com.solvd.laba.database.dao.TransactionsDAO;
import com.solvd.laba.database.model.Services;
import com.solvd.laba.database.model.Staff;
import com.solvd.laba.database.model.Transactions;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTransactionsDAOImpl implements TransactionsDAO {

    private final JDBCStaffDAOImpl jdbcStaffDAO = new JDBCStaffDAOImpl();
    private final JDBCServicesDAOImpl jdbcServicesDAO = new JDBCServicesDAOImpl();

    @Override
    public Transactions get(int id) {
        Connection connection = null;
        Transactions transaction = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM transactions WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                String type = rs.getString("type");
                int amount = rs.getInt("amount");
                String currency = rs.getString("currency");
                Timestamp dateTime = rs.getTimestamp("date_time");
                Staff staff = jdbcStaffDAO.get(rs.getInt("staff_id"));
                Services service = jdbcServicesDAO.get(rs.getInt("services_id"));

                transaction = new Transactions(gid, type, amount, currency, dateTime, staff, service);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return transaction;
    }

    @Override
    public List<Transactions> getAll() {
        Connection connection = null;
        List<Transactions> transactionsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM transactions";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                String type = rs.getString("type");
                int amount = rs.getInt("amount");
                String currency = rs.getString("currency");
                Timestamp dateTime = rs.getTimestamp("date_time");
                Staff staff = jdbcStaffDAO.get(rs.getInt("staff_id"));
                Services service = jdbcServicesDAO.get(rs.getInt("services_id"));

                Transactions transaction = new Transactions(gid, type, amount, currency, dateTime, staff, service);
                transactionsList.add(transaction);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return transactionsList;
    }

    @Override
    public void save(Transactions transaction) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO transactions (id, type, amount, currency, date_time, staff_id, services_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?) AS new " +
                    "ON DUPLICATE KEY " +
                    "UPDATE transactions SET type = new.type, amount = new.amount " +
                    "currency = new.currency, date_time = new.date_time, " +
                    "staff_id = new.staff_id, services_id = new.services_id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, transaction.getId());
            ps.setString(2, transaction.getType());
            ps.setInt(3, transaction.getAmount());
            ps.setString(4, transaction.getCurrency());
            ps.setTimestamp(5, transaction.getDateTime());
            ps.setInt(6, transaction.getStaff().getId());
            ps.setInt(7, transaction.getService().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Transactions transaction) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO transactions (id, type, amount, currency, date_time, staff_id, services_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, transaction.getId());
            ps.setString(2, transaction.getType());
            ps.setInt(3, transaction.getAmount());
            ps.setString(4, transaction.getCurrency());
            ps.setTimestamp(5, transaction.getDateTime());
            ps.setInt(6, transaction.getStaff().getId());
            ps.setInt(7, transaction.getService().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Transactions transaction) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "UPDATE transactions SET " +
                    "type = ?, " +
                    "amount = ? " +
                    "currency = ? " +
                    "date_time = ? " +
                    "staff_id = ? " +
                    "services_id = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, transaction.getType());
            ps.setInt(2, transaction.getAmount());
            ps.setString(3, transaction.getCurrency());
            ps.setTimestamp(4, transaction.getDateTime());
            ps.setInt(5, transaction.getStaff().getId());
            ps.setInt(6, transaction.getService().getId());
            ps.setInt(7, transaction.getId());
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

            String sql = "DELETE FROM transactions WHERE id = ?";
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
