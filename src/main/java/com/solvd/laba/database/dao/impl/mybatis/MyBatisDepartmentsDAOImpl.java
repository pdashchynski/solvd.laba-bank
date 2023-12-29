package com.solvd.laba.database.dao.impl.mybatis;

import com.solvd.laba.database.dao.DepartmentsDAO;
import com.solvd.laba.database.dao.impl.mybatis.mappers.DepartmentsMapper;
import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.database.model.Departments;
import com.solvd.laba.pooling.ConnectionPool;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyBatisDepartmentsDAOImpl implements DepartmentsDAO {

    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisDepartmentsDAOImpl() {
        this.sqlSessionFactory = MyBatisConfig.getSessionFactory();
    }

    @Override
    public Departments get(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentsMapper departmentsMapper = session.getMapper(DepartmentsMapper.class);
            return departmentsMapper.get(id);
        }
    }

    @Override
    public List<Departments> getAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentsMapper departmentsMapper = session.getMapper(DepartmentsMapper.class);
            return departmentsMapper.getAll();
        }
    }

    @Override
    public void save(Departments department) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentsMapper departmentsMapper = session.getMapper(DepartmentsMapper.class);
            departmentsMapper.save(department);
        }
    }

    @Override
    public void insert(Departments department) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentsMapper departmentsMapper = session.getMapper(DepartmentsMapper.class);
            departmentsMapper.insert(department);
        }
    }

    @Override
    public void update(Departments department) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentsMapper departmentsMapper = session.getMapper(DepartmentsMapper.class);
            departmentsMapper.update(department);
        }
    }

    @Override
    public void delete(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            DepartmentsMapper departmentsMapper = session.getMapper(DepartmentsMapper.class);
            departmentsMapper.delete(id);
        }
    }
}
