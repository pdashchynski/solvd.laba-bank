package com.solvd.laba.services;

import com.solvd.laba.database.dao.AccountsDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCAccountsDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisAccountsDAOImpl;
import com.solvd.laba.database.model.Accounts;

import java.util.List;

import static com.solvd.laba.services.SwitcherService.*;

public class AccountsService implements GenericCRUDService<Accounts> {

    private final AccountsDAO dao;

    public AccountsService() {
        if (isJdbcOn()) {
            this.dao = new JDBCAccountsDAOImpl();
        } else {
            this.dao = new MyBatisAccountsDAOImpl();
        }
    }

    @Override
    public Accounts get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Accounts> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Accounts account) {
        dao.save(account);
    }

    @Override
    public void insert(Accounts account) {
        dao.insert(account);
    }

    @Override
    public void update(Accounts account) {
        dao.update(account);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
