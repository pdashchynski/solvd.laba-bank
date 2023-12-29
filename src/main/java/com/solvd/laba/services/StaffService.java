package com.solvd.laba.services;

import com.solvd.laba.database.dao.StaffDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCStaffDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisStaffDAOImpl;
import com.solvd.laba.database.model.Staff;

import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class StaffService implements GenericCRUDService<Staff> {

    private final StaffDAO dao;

    public StaffService() {
        if (isJdbcOn()) {
            this.dao = new JDBCStaffDAOImpl();
        } else {
            this.dao = new MyBatisStaffDAOImpl();
        }
    }

    @Override
    public Staff get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Staff> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(Staff staff) {
        dao.save(staff);
    }

    @Override
    public void insert(Staff staff) {
        dao.insert(staff);
    }

    @Override
    public void update(Staff staff) {
        dao.update(staff);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
