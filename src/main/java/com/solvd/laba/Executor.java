package com.solvd.laba;

import com.solvd.laba.database.dao.impl.AccountsDAOImpl;
import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.pooling.ConnectionPool;

import java.sql.SQLException;

public class Executor {

    public static void main(String[] args) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        AccountsDAOImpl accountsDAO = new AccountsDAOImpl();
        Accounts account = null;
        try {
            account = accountsDAO.get(5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(account.toString());
    }
}
