package com.solvd.laba.services;

import com.solvd.laba.database.dao.PassportsDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCPassportsDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisPassportsDAOImpl;
import com.solvd.laba.database.model.Passports;
import com.solvd.laba.database.model.Persons;

import java.sql.Date;
import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class PassportsService implements GenericCRUDService<Passports> {

    private final PassportsDAO dao;
    private static final PersonsService PERSONS_SERVICE = new PersonsService();

    public PassportsService() {
        if (isJdbcOn()) {
            this.dao = new JDBCPassportsDAOImpl();
        } else {
            this.dao = new MyBatisPassportsDAOImpl();
        }
    }

    public Passports create(int id, Date fromDate, Date toDate, int personId) {
        Passports passport = new Passports();
        passport.setId(id);
        passport.setFromDate(fromDate);
        passport.setToDate(toDate);
        Persons person = PERSONS_SERVICE.get(personId);
        passport.setPerson(person);

        return passport;
    }

    @Override
    public Passports get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Passports> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Passports passport) {
        dao.save(passport);
    }

    @Override
    public void insert(Passports passport) {
        dao.insert(passport);
    }

    @Override
    public void update(Passports passport) {
        dao.update(passport);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
