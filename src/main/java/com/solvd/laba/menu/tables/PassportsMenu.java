package com.solvd.laba.menu.tables;

import com.solvd.laba.database.model.Departments;
import com.solvd.laba.database.model.Passports;
import com.solvd.laba.services.PassportsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class PassportsMenu {

    private static final Logger LOGGER = LogManager.getLogger(PassportsMenu.class);
    private final Scanner sc;
    private final PassportsService ps;

    public PassportsMenu() {
        this.ps = new PassportsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Passports");
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
        LOGGER.info(ps.get(id).toString());
    }

    private void printAll() {
        LOGGER.info(ps.getAll().toString());
    }

    private Passports create() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter From Date (YYYY-MM-DD)");
        Date fromDate = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter To Date (YYYY-MM-DD)");
        Date toDate = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter Person ID");
        int personId = sc.nextInt();
        return ps.create(id, fromDate, toDate, personId);
    }

    private void save() {
        ps.save(create());
    }

    private void insert() {
        ps.insert(create());
    }

    private void update() {
        ps.update(create());
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        ps.delete(id);
    }
}
