package com.solvd.laba.services;

import com.solvd.laba.database.dao.ServicesDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCServicesDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisServicesDAOImpl;
import com.solvd.laba.database.model.Services;

import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class ServicesService implements GenericCRUDService<Services> {
    
    private final ServicesDAO dao;

    public ServicesService() {
        if (isJdbcOn()) {
            this.dao = new JDBCServicesDAOImpl();
        } else {
            this.dao = new MyBatisServicesDAOImpl();
        }
    }

    @Override
    public Services get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Services> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Services service) {
        dao.save(service);
    }

    @Override
    public void insert(Services service) {
        dao.insert(service);
    }

    @Override
    public void update(Services service) {
        dao.update(service);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
