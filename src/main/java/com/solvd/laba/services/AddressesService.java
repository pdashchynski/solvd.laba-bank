package com.solvd.laba.services;

import com.solvd.laba.database.dao.AddressesDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCAddressesDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisAddressesDAOImpl;
import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.database.model.Clients;

import java.sql.Date;
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

    public Addresses create(int id, String country, String city, String postalCode) {
        Addresses address = new Addresses();
        address.setId(id);
        address.setCountry(country);
        address.setCity(city);
        address.setPostalCode(postalCode);

        return address;
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
