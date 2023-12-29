package com.solvd.laba.services;

import com.solvd.laba.database.dao.PersonsDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCPersonsDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisPersonsDAOImpl;
import com.solvd.laba.database.model.Persons;

import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class PersonsService implements GenericCRUDService<Persons> {

    private final PersonsDAO dao;

    public PersonsService() {
        if (isJdbcOn()) {
            this.dao = new JDBCPersonsDAOImpl();
        } else {
            this.dao = new MyBatisPersonsDAOImpl();
        }
    }

    @Override
    public Persons get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Persons> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Persons person) {
        dao.save(person);
    }

    @Override
    public void insert(Persons person) {
        dao.insert(person);
    }

    @Override
    public void update(Persons person) {
        dao.update(person);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
