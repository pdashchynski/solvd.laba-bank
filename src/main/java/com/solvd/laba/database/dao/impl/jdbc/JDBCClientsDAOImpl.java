package com.solvd.laba.database.dao.impl.jdbc;

import com.solvd.laba.database.dao.ClientsDAO;
import com.solvd.laba.database.model.Clients;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCClientsDAOImpl implements ClientsDAO {

    private final JDBCPersonsDAOImpl jdbcPersonsDAO = new JDBCPersonsDAOImpl();

    @Override
    public Clients get(int id) {
        Connection connection = null;
        Clients client = null;
        try {
            connection = ConnectionPool.getConnection();


            String sql = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Persons person = jdbcPersonsDAO.get(rs.getInt("persons_id"));
                String status = rs.getString("status");

                client = new Clients(gid, person, status);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return client;
    }

    @Override
    public List<Clients> getAll() {
        Connection connection = null;
        List<Clients> clientsList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();

            String sql = "SELECT * FROM clients";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int gid = rs.getInt("id");
                Persons person = jdbcPersonsDAO.get(rs.getInt("persons_id"));
                String status = rs.getString("status");

                Clients client = new Clients(gid, person, status);
                clientsList.add(client);
            }
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return clientsList;
    }

    @Override
    public void save(Clients client) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO clients (id, persons_id, status) " +
                    "VALUES (?, ?, ?) AS new " +
                    "ON DUPLICATE KEY " +
                    "UPDATE clients SET persons_id = new.persons_id, status = new.status";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, client.getId());
            ps.setInt(2, client.getPerson().getId());
            ps.setString(3, client.getStatus());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void insert(Clients client) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "INSERT INTO clients (id, persons_id, status) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, client.getId());
            ps.setInt(2, client.getPerson().getId());
            ps.setString(3, client.getStatus());
            ps.executeUpdate();
        } catch (InterruptedException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Clients client) {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            String sql = "UPDATE clients SET " +
                    "persons_id = ?, " +
                    "status = ? " +
                    "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, client.getPerson().getId());
            ps.setString(2, client.getStatus());
            ps.setInt(3, client.getId());
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

            String sql = "DELETE FROM clients WHERE id = ?";
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
