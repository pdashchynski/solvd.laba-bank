package com.solvd.laba.database.dao.impl.mybatis;

import com.solvd.laba.database.dao.AddressesDAO;
import com.solvd.laba.database.dao.impl.mybatis.mappers.AccountsMapper;
import com.solvd.laba.database.dao.impl.mybatis.mappers.AddressesMapper;
import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.pooling.ConnectionPool;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisAddressesDAOImpl implements AddressesDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisAddressesDAOImpl() {
        this.sqlSessionFactory = MyBatisConfig.getSessionFactory();
    }

    @Override
    public Addresses get(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressesMapper addressesMapper = session.getMapper(AddressesMapper.class);
            return addressesMapper.get(id);
        }
    }

    @Override
    public List<Addresses> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressesMapper addressesMapper = session.getMapper(AddressesMapper.class);
            return addressesMapper.getAll();
        }
    }

    @Override
    public void save(Addresses address) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressesMapper addressesMapper = session.getMapper(AddressesMapper.class);
            addressesMapper.save(address);
        }
    }

    @Override
    public void insert(Addresses address) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressesMapper addressesMapper = session.getMapper(AddressesMapper.class);
            addressesMapper.insert(address);
        }
    }

    @Override
    public void update(Addresses address) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressesMapper addressesMapper = session.getMapper(AddressesMapper.class);
            addressesMapper.update(address);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AddressesMapper addressesMapper = session.getMapper(AddressesMapper.class);
            addressesMapper.delete(id);
        }
    }
}