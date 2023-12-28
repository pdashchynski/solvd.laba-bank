package com.solvd.laba.database.dao.impl;

import com.solvd.laba.database.dao.CardsDAO;
import com.solvd.laba.database.model.Cards;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardsDAOImpl implements CardsDAO {

    @Override
    public Cards get(int id) throws SQLException {
        Connection connection = null;
        Cards card = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM cards WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            Date fromDate = rs.getDate("from_date");
            Date toDate = rs.getDate("to_date");
            String type = rs.getString("type");
            int accountsId = rs.getInt("accounts_idaccounts");

            card = new Cards(gid, fromDate, toDate, type, accountsId);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return card;
    }

    @Override
    public List<Cards> getAll() throws SQLException {
        Connection connection = null;
        List<Cards> cardsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM cards";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            Date fromDate = rs.getDate("from_date");
            Date toDate = rs.getDate("to_date");
            String type = rs.getString("type");
            int accountsId = rs.getInt("accounts_idaccounts");

            Cards card = new Cards(gid, fromDate, toDate, type, accountsId);
            cardsList.add(card);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return cardsList;
    }

    @Override
    public void save(Cards card) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM cards WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, card.getId());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            //update(card);
        } else {
            insert(card);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Cards card) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO cards (id, from_date, to_date, type, accounts_idaccounts) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, card.getId());
        ps.setDate(2, card.getFromDate());
        ps.setDate(3, card.getToDate());
        ps.setString(4, card.getType());
        ps.setInt(5, card.getAccountsId());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Cards card, int id) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM cards WHERE id = ?";
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
