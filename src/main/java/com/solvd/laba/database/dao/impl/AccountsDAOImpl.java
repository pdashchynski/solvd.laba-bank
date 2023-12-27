package com.solvd.laba.database.dao.impl;

import com.solvd.laba.database.dao.AccountsDAO;
import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsDAOImpl implements AccountsDAO {

    @Override
    public Accounts get(int id) throws SQLException {
        Connection connection = null;
        Accounts account = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM accounts WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            Date fromDate = rs.getDate("from_date");
            Date toDate = rs.getDate("to_date");
            int balance = rs.getInt("balance");
            String currency = rs.getString("currency");
            int clientsId = rs.getInt("clients_idclients");

            account = new Accounts(gid, fromDate, toDate, balance, currency, clientsId);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return account;
    }

    @Override
    public List<Accounts> getAll() throws SQLException {
        Connection connection = null;
        List<Accounts> accountsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM accounts";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            Date fromDate = rs.getDate("from_date");
            Date toDate = rs.getDate("to_date");
            int balance = rs.getInt("balance");
            String currency = rs.getString("currency");
            int clientsId = rs.getInt("clients_idclients");

            Accounts account = new Accounts(gid, fromDate, toDate, balance, currency, clientsId);


            accountsList.add(account);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return accountsList;
    }

    @Override
    public void save(Accounts account) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM accounts WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, account.getId());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            update(account);
        } else {
            insert(account);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Accounts account) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO accounts (id, from_date, to_date, balance, currency, clients_idclients) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, account.getId());
        ps.setDate(2, account.getFromDate());
        ps.setDate(3, account.getToDate());
        ps.setInt(4, account.getBalance());
        ps.setString(5, account.getCurrency());
        ps.setInt(6, account.getClientsId());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Accounts account, String[] params) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE accounts set " +
                "id = ?, " +
                "from_date = ?, " +
                "to_date = ?, " +
                "balance = ?, " +
                "currency = ?, " +
                "clients_idclients = ?) ";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, account.getId());
        ps.setDate(2, account.getFromDate());
        ps.setDate(3, account.getToDate());
        ps.setInt(4, account.getBalance());
        ps.setString(5, account.getCurrency());
        ps.setInt(6, account.getClientsId());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void updateT(T t) {
        
    }

    @Override
    public void delete(Accounts account) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM accounts WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, account.getId());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
