package com.solvd.laba.services;

import com.solvd.laba.database.dao.DepartmentsDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCDepartmentsDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisDepartmentsDAOImpl;
import com.solvd.laba.database.model.Departments;

import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class DepartmentsService implements GenericCRUDService<Departments> {

    private final DepartmentsDAO dao;

    public DepartmentsService() {
        if (isJdbcOn()) {
            this.dao = new JDBCDepartmentsDAOImpl();
        } else {
            this.dao = new MyBatisDepartmentsDAOImpl();
        }
    }

    @Override
    public Departments get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Departments> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Departments department) {
        dao.save(department);
    }

    @Override
    public void insert(Departments department) {
        dao.insert(department);
    }

    @Override
    public void update(Departments department) {
        dao.update(department);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
