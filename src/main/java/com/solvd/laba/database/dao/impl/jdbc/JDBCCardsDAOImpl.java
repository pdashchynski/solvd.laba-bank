package com.solvd.laba.database.dao.impl.jdbc;

import com.solvd.laba.database.dao.CardsDAO;
import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.database.model.Cards;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCardsDAOImpl implements CardsDAO {

    private final JDBCAccountsDAOImpl jdbcAccountsDAO = new JDBCAccountsDAOImpl();

    @Override
    public Cards get(int id) {
        Connection connection = null;
        Cards card = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM cards WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Date fromDate = rs.getDate("from_date");
                Date toDate = rs.getDate("to_date");
                String type = rs.getString("type");
                Accounts account = jdbcAccountsDAO.get(rs.getInt("accounts_id"));

                card = new Cards(gid, fromDate, toDate, type, account);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return card;
    }

    @Override
    public List<Cards> getAll() {
        Connection connection = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM cards";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Date fromDate = rs.getDate("from_date");
                Date toDate = rs.getDate("to_date");
                String type = rs.getString("type");
                Accounts account = jdbcAccountsDAO.get(rs.getInt("accounts_id"));

                Cards card = new Cards(gid, fromDate, toDate, type, account);
                cardsList.add(card);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return cardsList;
    }

    @Override
    public void save(Cards card) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO cards (id, from_date, to_date, type, " +
                    "accounts_id, accounts_clients_id, accounts_clients_persons_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?) AS new" +
                    "ON DUPLICATE KEY " +
                    "UPDATE cards SET from_date = new.from_date, to_date = new.to_date, " +
                    "type = new.type, " +
                    "accounts_id = new.accounts_id, " +
                    "accounts_clients_id = new.accounts_clients_id, " +
                    "accounts_clients_persons_id = new.accounts_clients_persons_id";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, card.getId());
            ps.setDate(2, card.getFromDate());
            ps.setDate(3, card.getToDate());
            ps.setString(4, card.getType());
            ps.setInt(5, card.getAccount().getId());
            ps.setInt(6, card.getAccount().getClient().getId());
            ps.setInt(7, card.getAccount().getClient().getPerson().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Cards card) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO cards (id, from_date, to_date, type, " +
                    "accounts_id, accounts_clients_id, accounts_clients_persons_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, card.getId());
            ps.setDate(2, card.getFromDate());
            ps.setDate(3, card.getToDate());
            ps.setString(4, card.getType());
            ps.setInt(5, card.getAccount().getId());
            ps.setInt(6, card.getAccount().getClient().getId());
            ps.setInt(7, card.getAccount().getClient().getPerson().getId());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Cards card) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "UPDATE cards SET " +
                    "from_date = ?, " +
                    "to_date = ?, " +
                    "type = ?, " +
                    "accounts_id = ?, " +
                    "accounts_clients_id = ?, " +
                    "accounts_clients_persons_id = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setDate(1, card.getFromDate());
            ps.setDate(2, card.getToDate());
            ps.setString(3, card.getType());
            ps.setInt(4, card.getAccount().getId());
            ps.setInt(5, card.getAccount().getClient().getId());
            ps.setInt(6, card.getAccount().getClient().getPerson().getId());
            ps.setInt(7, card.getId());
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

            String sql = "DELETE FROM cards WHERE id = ?";
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
