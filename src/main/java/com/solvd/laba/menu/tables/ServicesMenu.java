package com.solvd.laba.menu.tables;

import com.solvd.laba.database.model.Persons;
import com.solvd.laba.database.model.Services;
import com.solvd.laba.services.ServicesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Scanner;

public class ServicesMenu {

    private static final Logger LOGGER = LogManager.getLogger(ServicesMenu.class);
    private final Scanner sc;
    private final ServicesService ss;

    public ServicesMenu() {
        this.ss = new ServicesService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Services");
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

    private Services create() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter Name");
        String name = sc.nextLine();
        LOGGER.info("Enter Price");
        int price = sc.nextInt();
        return ss.create(id, name, price);
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
