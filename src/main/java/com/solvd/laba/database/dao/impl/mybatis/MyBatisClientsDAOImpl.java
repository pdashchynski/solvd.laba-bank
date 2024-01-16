package com.solvd.laba.database.dao.impl.mybatis;

import com.solvd.laba.database.dao.ClientsDAO;
import com.solvd.laba.database.dao.impl.mybatis.mappers.ClientsMapper;
import com.solvd.laba.database.model.Clients;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisClientsDAOImpl implements ClientsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisClientsDAOImpl() {
        this.sqlSessionFactory = MyBatisConfig.getSessionFactory();
    }

    @Override
    public Clients get(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClientsMapper clientsMapper = session.getMapper(ClientsMapper.class);
            return clientsMapper.get(id);
        }
    }

    @Override
    public List<Clients> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClientsMapper clientsMapper = session.getMapper(ClientsMapper.class);
            return clientsMapper.getAll();
        }
    }

    @Override
    public void save(Clients client) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClientsMapper clientsMapper = session.getMapper(ClientsMapper.class);
            clientsMapper.save(client);
        }
    }

    @Override
    public void insert(Clients client) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClientsMapper clientsMapper = session.getMapper(ClientsMapper.class);
            clientsMapper.insert(client);
        }
    }

    @Override
    public void update(Clients client) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClientsMapper clientsMapper = session.getMapper(ClientsMapper.class);
            clientsMapper.update(client);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ClientsMapper clientsMapper = session.getMapper(ClientsMapper.class);
            clientsMapper.delete(id);
        }
    }
}
