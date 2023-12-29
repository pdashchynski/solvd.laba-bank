package com.solvd.laba.menu.tables;

import com.solvd.laba.database.model.Clients;
import com.solvd.laba.database.model.Departments;
import com.solvd.laba.services.DepartmentsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Time;
import java.util.Scanner;

public class DepartmentsMenu {

    private static final Logger LOGGER = LogManager.getLogger(DepartmentsMenu.class);
    private final Scanner sc;
    private final DepartmentsService ds;

    public DepartmentsMenu() {
        this.ds = new DepartmentsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Departments");
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
        LOGGER.info(ds.get(id).toString());
    }

    private void printAll() {
        LOGGER.info(ds.getAll().toString());
    }

    private Departments create() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter Address ID");
        int addressId = sc.nextInt();
        LOGGER.info("Enter Open Time (HH:MI:SS)");
        Time openTime = Time.valueOf(sc.nextLine());
        LOGGER.info("Enter Close Time (HH:MI:SS)");
        Time closeTime = Time.valueOf(sc.nextLine());
        return ds.create(id, addressId, openTime, closeTime);
    }

    private void save() {
        ds.save(create());
    }

    private void insert() {
        ds.insert(create());
    }

    private void update() {
        ds.update(create());
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        ds.delete(id);
    }
}
