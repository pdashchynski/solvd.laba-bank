package com.solvd.laba.database.dao.impl.mybatis;

import com.solvd.laba.database.dao.AccountsDAO;
import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.database.model.Clients;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyBatisAccountsDAOImpl implements AccountsDAO {

    private final MyBatisClientsDAOImpl jdbcClientsDAO = new MyBatisClientsDAOImpl();

    @Override
    public Accounts get(int id) {
        Connection connection = null;
        Accounts account = null;
        try {
            connection = ConnectionPool.getConnection();

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
                Clients client = jdbcClientsDAO.get(rs.getInt("clients_id"));

                account = new Accounts(gid, fromDate, toDate, balance, currency, client);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return account;
    }

    @Override
    public List<Accounts> getAll() {
        Connection connection = null;
        List<Accounts> accountsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM accounts";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Date fromDate = rs.getDate("from_date");
                Date toDate = rs.getDate("to_date");
                int balance = rs.getInt("balance");
                String currency = rs.getString("currency");
                Clients client = jdbcClientsDAO.get(rs.getInt("clients_id"));

                Accounts account = new Accounts(gid, fromDate, toDate, balance, currency, client);
                accountsList.add(account);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return accountsList;
    }

    @Override
    public void save(Accounts account) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO accounts (id, from_date, to_date, balance, currency, " +
                    "clients_id, clients_persons_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?) AS new" +
                    "ON DUPLICATE KEY " +
                    "UPDATE accounts SET from_date = new.from_date, to_date = new.to_date, " +
                    "balance = new.balance, currency = new.currency, " +
                    "clients_id = new.clients_id, clients_persons_id = new.clients_persons_id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, account.getId());
            ps.setDate(2, account.getFromDate());
            ps.setDate(3, account.getToDate());
            ps.setInt(4, account.getBalance());
            ps.setString(5, account.getCurrency());
            ps.setInt(6, account.getClient().getId());
            ps.setInt(7, account.getClient().getPerson().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Accounts account) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO accounts (id, from_date, to_date, balance, currency, " +
                    "clients_id, clients_persons_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, account.getId());
            ps.setDate(2, account.getFromDate());
            ps.setDate(3, account.getToDate());
            ps.setInt(4, account.getBalance());
            ps.setString(5, account.getCurrency());
            ps.setInt(6, account.getClient().getId());
            ps.setInt(7, account.getClient().getPerson().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Accounts account) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "UPDATE accounts SET " +
                    "from_date = ?, " +
                    "to_date = ?, " +
                    "balance = ?, " +
                    "currency = ?, " +
                    "clients_id = ?, " +
                    "clients_persons_id = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setDate(1, account.getFromDate());
            ps.setDate(2, account.getToDate());
            ps.setInt(3, account.getBalance());
            ps.setString(4, account.getCurrency());
            ps.setInt(5, account.getClient().getId());
            ps.setInt(6, account.getClient().getPerson().getId());
            ps.setInt(7, account.getId());
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

            String sql = "DELETE FROM accounts WHERE id = ?";
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
