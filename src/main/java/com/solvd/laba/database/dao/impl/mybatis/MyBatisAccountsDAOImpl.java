package com.solvd.laba.database.dao.impl.mybatis;

import com.solvd.laba.database.dao.AccountsDAO;
import com.solvd.laba.database.dao.impl.mybatis.mappers.AccountsMapper;
import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.database.model.Clients;
import com.solvd.laba.pooling.ConnectionPool;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyBatisAccountsDAOImpl implements AccountsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisAccountsDAOImpl() {
        this.sqlSessionFactory = MyBatisConfig.getSessionFactory();
    }

    @Override
    public Accounts get(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountsMapper accountsMapper = session.getMapper(AccountsMapper.class);
            return accountsMapper.get(id);
        }
    }

    @Override
    public List<Accounts> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountsMapper accountsMapper = session.getMapper(AccountsMapper.class);
            return accountsMapper.getAll();
        }
    }

    @Override
    public void save(Accounts account) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountsMapper accountsMapper = session.getMapper(AccountsMapper.class);
            accountsMapper.save(account);
            session.commit();
        }
    }

    @Override
    public void insert(Accounts account) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountsMapper accountsMapper = session.getMapper(AccountsMapper.class);
            accountsMapper.insert(account);
            session.commit();
        }
    }

    @Override
    public void update(Accounts account) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountsMapper accountsMapper = session.getMapper(AccountsMapper.class);
            accountsMapper.update(account);
            session.commit();
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountsMapper accountsMapper = session.getMapper(AccountsMapper.class);
            accountsMapper.delete(id);
            session.commit();
        }
    }
}
