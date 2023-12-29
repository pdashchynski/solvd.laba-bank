package com.solvd.laba.services;

import com.solvd.laba.database.dao.StaffDAO;
import com.solvd.laba.database.dao.impl.jdbc.JDBCStaffDAOImpl;
import com.solvd.laba.database.dao.impl.mybatis.MyBatisStaffDAOImpl;
import com.solvd.laba.database.model.Departments;
import com.solvd.laba.database.model.Staff;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.database.model.Staff;

import java.sql.Date;
import java.util.List;

import static com.solvd.laba.services.SwitcherService.isJdbcOn;

public class StaffService implements GenericCRUDService<Staff> {

    private final StaffDAO dao;
    private static final PersonsService PERSONS_SERVICE = new PersonsService();
    private static final DepartmentsService DEPARTMENTS_SERVICE = new DepartmentsService();

    public StaffService() {
        if (isJdbcOn()) {
            this.dao = new JDBCStaffDAOImpl();
        } else {
            this.dao = new MyBatisStaffDAOImpl();
        }
    }

    public Staff create(int id, Date dateHired, String position,
                        int salary, int personId, int departmentId) {
        Staff staff = new Staff();
        staff.setId(id);
        staff.setDateHired(dateHired);
        staff.setPosition(position);
        staff.setSalary(salary);
        Persons person = PERSONS_SERVICE.get(personId);
        staff.setPerson(person);
        Departments department = DEPARTMENTS_SERVICE.get(departmentId);
        staff.setDepartment(department);

        return staff;
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
