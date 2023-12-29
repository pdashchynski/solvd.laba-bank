package com.solvd.laba.services;

import com.solvd.laba.database.dao.TransactionsDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCTransactionsDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisTransactionsDAOImpl;
import com.solvd.laba.database.model.Transactions;

import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class TransactionsService implements GenericCRUDService<Transactions> {

    private final TransactionsDAO dao;

    public TransactionsService() {
        if (isJdbcOn()) {
            this.dao = new JDBCTransactionsDAOImpl();
        } else {
            this.dao = new MyBatisTransactionsDAOImpl();
        }
    }

    @Override
    public Transactions get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Transactions> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Transactions transaction) {
        dao.save(transaction);
    }

    @Override
    public void insert(Transactions transaction) {
        dao.insert(transaction);
    }

    @Override
    public void update(Transactions transaction) {
        dao.update(transaction);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
