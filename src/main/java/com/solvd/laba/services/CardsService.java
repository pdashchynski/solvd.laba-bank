package com.solvd.laba.services;

import com.solvd.laba.database.dao.CardsDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCCardsDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisCardsDAOImpl;
import com.solvd.laba.database.model.Cards;

import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class CardsService implements GenericCRUDService<Cards> {

    private final CardsDAO dao;

    public CardsService() {
        if (isJdbcOn()) {
            this.dao = new JDBCCardsDAOImpl();
        } else {
            this.dao = new MyBatisCardsDAOImpl();
        }
    }

    @Override
    public Cards get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Cards> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Cards card) {
        dao.save(card);
    }

    @Override
    public void insert(Cards card) {
        dao.insert(card);
    }

    @Override
    public void update(Cards card) {
        dao.update(card);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
