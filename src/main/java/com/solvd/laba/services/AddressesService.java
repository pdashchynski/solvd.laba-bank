package com.solvd.laba.services;

import com.solvd.laba.database.dao.AddressesDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCAddressesDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisAddressesDAOImpl;
import com.solvd.laba.database.model.Addresses;

import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class AddressesService implements GenericCRUDService<Addresses> {

    private final AddressesDAO dao;

    public AddressesService() {
        if (isJdbcOn()) {
            this.dao = new JDBCAddressesDAOImpl();
        } else {
            this.dao = new MyBatisAddressesDAOImpl();
        }
    }

    @Override
    public Addresses get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Addresses> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Addresses address) {
        dao.save(address);
    }

    @Override
    public void insert(Addresses address) {
        dao.insert(address);
    }

    @Override
    public void update(Addresses address) {
        dao.update(address);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
