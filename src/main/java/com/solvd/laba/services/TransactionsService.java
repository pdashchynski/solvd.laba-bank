package com.solvd.laba.services;

import com.solvd.laba.database.dao.TransactionsDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCTransactionsDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisTransactionsDAOImpl;
import com.solvd.laba.database.model.*;
import com.solvd.laba.database.model.Transactions;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class TransactionsService implements GenericCRUDService<Transactions> {

    private final TransactionsDAO dao;
    private static final StaffService STAFF_SERVICE = new StaffService();
    private static final ServicesService SERVICES_SERVICE = new ServicesService();

    public TransactionsService() {
        if (isJdbcOn()) {
            this.dao = new JDBCTransactionsDAOImpl();
        } else {
            this.dao = new MyBatisTransactionsDAOImpl();
        }
    }

    public Transactions create(int id, String type, int amount, String currency,
                               Timestamp dateTime, int staffId, int serviceId) {
        Transactions transaction = new Transactions();
        transaction.setId(id);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setCurrency(currency);
        transaction.setDateTime(dateTime);
        Staff staff = STAFF_SERVICE.get(staffId);
        transaction.setStaff(staff);
        Services service = SERVICES_SERVICE.get(serviceId);
        transaction.setService(service);

        return transaction;
    }

    @Override
    public Transactions get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Transactions> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Transactions transaction) {
        dao.save(transaction);
    }

    @Override
    public void insert(Transactions transaction) {
        dao.insert(transaction);
    }

    @Override
    public void update(Transactions transaction) {
        dao.update(transaction);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
