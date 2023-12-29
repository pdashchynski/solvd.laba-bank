package com.solvd.laba.services;

import com.solvd.laba.database.dao.ClientsDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCClientsDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisClientsDAOImpl;
import com.solvd.laba.database.model.Clients;

import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class ClientsService implements GenericCRUDService<Clients> {

    private final ClientsDAO dao;

    public ClientsService() {
        if (isJdbcOn()) {
            this.dao = new JDBCClientsDAOImpl();
        } else {
            this.dao = new MyBatisClientsDAOImpl();
        }
    }

    @Override
    public Clients get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Clients> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Clients client) {
        dao.save(client);
    }

    @Override
    public void insert(Clients client) {
        dao.insert(client);
    }

    @Override
    public void update(Clients client) {
        dao.update(client);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
