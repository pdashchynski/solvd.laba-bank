package com.solvd.laba.pooling;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {

    private volatile static ConnectionPool instance;
    private static final int POOL_SIZE = 5;
    private static final BlockingQueue<Connection> CONNECTION_POOL = new ArrayBlockingQueue<>(POOL_SIZE);
    private static final Properties PROPERTIES = new Properties();
    private static final String PATH = "src/main/resources/config.properties";

    static {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            PROPERTIES.load(new FileInputStream(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionPool(int poolSize) {
        String url = PROPERTIES.getProperty("url");
        String username = PROPERTIES.getProperty("username");
        String password = PROPERTIES.getProperty("password");

        for (int i = 0; i < poolSize; i++) {
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

    public static void releaseConnection(Connection connection) {
        CONNECTION_POOL.offer(connection);
    }

    public int getPoolSize() {
        return POOL_SIZE;
    }
}

