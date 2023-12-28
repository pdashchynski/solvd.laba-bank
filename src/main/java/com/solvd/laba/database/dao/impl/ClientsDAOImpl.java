package com.solvd.laba.database.dao.impl;

import com.solvd.laba.database.dao.ClientsDAO;
import com.solvd.laba.database.model.Clients;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsDAOImpl implements ClientsDAO {

    @Override
    public Clients get(int id) throws SQLException {
        Connection connection = null;
        Clients client = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM clients WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            int personsId = rs.getInt("persons_idpersons");
            String status = rs.getString("status");

            client = new Clients(gid, personsId, status);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return client;
    }

    @Override
    public List<Clients> getAll() throws SQLException {
        Connection connection = null;
        List<Clients> clientsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM clients";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int gid = rs.getInt("id");
            int personsId = rs.getInt("persons_idpersons");
            String status = rs.getString("status");

            Clients client = new Clients(gid, personsId, status);
            clientsList.add(client);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return clientsList;
    }

    @Override
    public void save(Clients client) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT * FROM clients WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, client.getId());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            //update(client);
        } else {
            insert(client);
        }

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Clients client) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO clients (id, persons_idpersons, status) " +
                "VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, client.getId());
        ps.setInt(2, client.getPersonsId());
        ps.setString(3, client.getStatus());
        ps.executeUpdate();

        try {
            ConnectionPool.releaseConnection(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Clients client, int id) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM clients WHERE id = ?";
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
