package com.solvd.laba.pooling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {

    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private volatile static ConnectionPool instance;
    private static final int POOL_SIZE = 5;
    private static final BlockingQueue<Connection> CONNECTION_POOL = new ArrayBlockingQueue<>(POOL_SIZE);

    private ConnectionPool(int poolSize) {
        String url = PropertyParser.getUrl();
        String username = PropertyParser.getUsername();
        String password = PropertyParser.getPassword();
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                CONNECTION_POOL.add(createConnection(url, username, password));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null)
            synchronized (ConnectionPool.class) {
                if (instance == null)
                    instance = new ConnectionPool(POOL_SIZE);
            }
        return instance;
    }

    private static Connection
    createConnection(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url ,username, password);
    }

    public static Connection getConnection() throws InterruptedException {
        return CONNECTION_POOL.poll();
    }

    public static void releaseConnection(Connection connection) throws InterruptedException {
        CONNECTION_POOL.offer(connection);
    }

    public int getPoolSize() {
        return POOL_SIZE;
    }
}

