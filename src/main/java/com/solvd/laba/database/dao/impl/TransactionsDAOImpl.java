package com.solvd.laba.database.dao.impl;

import com.solvd.laba.database.dao.TransactionDAO;
import com.solvd.laba.database.model.Services;
import com.solvd.laba.database.model.Transactions;
import com.solvd.laba.database.model.Transactions;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionsDAOImpl implements TransactionDAO {

    @Override
    public Transactions get(int id) throws SQLException {
        Connection connection = null;
        Transactions transaction = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
            int staffId = rs.getInt("staff_idstaff");
            int servicesId = rs.getInt("services_idservices");

            transaction = new Transactions(gid, type, amount, currency, dateTime, staffId, servicesId);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return transaction;
    }

    @Override
    public List<Transactions> getAll() throws SQLException {
        Connection connection = null;
        List<Transactions> transactionsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM transactions";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            String type = rs.getString("type");
            int amount = rs.getInt("amount");
            String currency = rs.getString("currency");
            Timestamp dateTime = rs.getTimestamp("date_time");
            int staffId = rs.getInt("staff_idstaff");
            int servicesId = rs.getInt("services_idservices");

            Transactions transaction = new Transactions(gid, type, amount, currency, dateTime, staffId, servicesId);
            transactionsList.add(transaction);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return transactionsList;
    }

    @Override
    public void save(Transactions transaction) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM transactions WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, transaction.getId());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            //update(transaction);
        } else {
            insert(transaction);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Transactions transaction) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO transactions (id, type, amount, currency, date_time, staff_idstaff, services_idservices) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, transaction.getId());
        ps.setString(2, transaction.getType());
        ps.setInt(3, transaction.getAmount());
        ps.setString(4, transaction.getCurrency());
        ps.setTimestamp(5, transaction.getDateTime());
        ps.setInt(6, transaction.getStaffId());
        ps.setInt(7, transaction.getServicesId());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Transactions transaction, int id) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM transactions WHERE id = ?";
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
