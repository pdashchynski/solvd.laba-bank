package com.solvd.laba.menu.tables;

import com.solvd.laba.database.model.Services;
import com.solvd.laba.database.model.Staff;
import com.solvd.laba.services.StaffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Scanner;

public class StaffMenu {

    private static final Logger LOGGER = LogManager.getLogger(StaffMenu.class);
    private final Scanner sc;
    private final StaffService ss;

    public StaffMenu() {
        this.ss = new StaffService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Staff");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    print();
                    break;
                case 2:
                    printAll();
                    break;
                case 3:
                    save();
                    break;
                case 4:
                    insert();
                    break;
                case 5:
                    update();
                    break;
                case 6:
                    delete();
                    break;
                case 0:
                    isExit = true;
                    LOGGER.info("Back to Tables Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Menu Input");
                    break;
            }
        }
    }

    private void print() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info(ss.get(id).toString());
    }

    private void printAll() {
        LOGGER.info(ss.getAll().toString());
    }

    private Staff create() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter Date Hired (YYYY-MM-DD)");
        Date dateHired = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter Position");
        String position = sc.nextLine();
        LOGGER.info("Enter Salary");
        int salary = sc.nextInt();
        LOGGER.info("Enter Person Id");
        int personId = sc.nextInt();
        LOGGER.info("Enter Department Id");
        int departmentId = sc.nextInt();
        return ss.create(id, dateHired, position, salary, personId, departmentId);
    }

    private void save() {
        ss.save(create());
    }

    private void insert() {
        ss.insert(create());
    }

    private void update() {
        ss.update(create());
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        ss.delete(id);
    }
}
