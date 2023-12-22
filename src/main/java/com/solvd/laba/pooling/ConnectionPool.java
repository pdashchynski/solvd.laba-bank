package com.solvd.laba.pooling;

import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {
    private volatile static ConnectionPool instance;
    private static final int POOL_SIZE = 5;
    private static final String URL = "test/url";
    private static final BlockingQueue<Connection> CONNECTION_POOL = new ArrayBlockingQueue<>(POOL_SIZE);

    private ConnectionPool(int poolSize) {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                CONNECTION_POOL.add(createConnection(i, URL));
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

    private static Connection createConnection(int number, String url) throws SQLException {
        return new Connection(number, url);
    }

    public static Connection getConnection() throws InterruptedException {
        return CONNECTION_POOL.poll(10000, TimeUnit.MILLISECONDS);
    }

    public static void releaseConnection(Connection connection) throws InterruptedException {
        CONNECTION_POOL.offer(connection, 1000, TimeUnit.MILLISECONDS);
    }

    public int getPoolSize() {
        return POOL_SIZE;
    }
}

