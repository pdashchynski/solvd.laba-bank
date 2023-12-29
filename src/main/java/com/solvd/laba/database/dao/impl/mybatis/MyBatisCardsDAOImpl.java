package com.solvd.laba.database.dao.impl.mybatis;

import com.solvd.laba.database.dao.CardsDAO;
import com.solvd.laba.database.dao.impl.mybatis.mappers.AddressesMapper;
import com.solvd.laba.database.dao.impl.mybatis.mappers.CardsMapper;
import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.database.model.Cards;
import com.solvd.laba.pooling.ConnectionPool;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyBatisCardsDAOImpl implements CardsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisCardsDAOImpl() {
        this.sqlSessionFactory = MyBatisConfig.getSessionFactory();
    }

    @Override
    public Cards get(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CardsMapper cardsMapper = session.getMapper(CardsMapper.class);
            return cardsMapper.get(id);
        }
    }

    @Override
    public List<Cards> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CardsMapper cardsMapper = session.getMapper(CardsMapper.class);
            return cardsMapper.getAll();
        }
    }

    @Override
    public void save(Cards card) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CardsMapper cardsMapper = session.getMapper(CardsMapper.class);
            cardsMapper.save(card);
        }
    }

    @Override
    public void insert(Cards card) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CardsMapper cardsMapper = session.getMapper(CardsMapper.class);
            cardsMapper.insert(card);
        }
    }

    @Override
    public void update(Cards card) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CardsMapper cardsMapper = session.getMapper(CardsMapper.class);
            cardsMapper.update(card);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CardsMapper cardsMapper = session.getMapper(CardsMapper.class);
            cardsMapper.delete(id);
        }
    }
}
