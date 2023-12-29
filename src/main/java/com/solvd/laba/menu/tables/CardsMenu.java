package com.solvd.laba.menu.tables;

import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.database.model.Cards;
import com.solvd.laba.services.CardsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Scanner;

public class CardsMenu {

    private static final Logger LOGGER = LogManager.getLogger(CardsMenu.class);
    private final Scanner sc;
    private final CardsService cs;

    public CardsMenu() {
        this.cs = new CardsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Cards");
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
        LOGGER.info(cs.get(id).toString());
    }

    private void printAll() {
        LOGGER.info(cs.getAll().toString());
    }

    private Cards create() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter From Date (YYYY-MM-DD)");
        Date fromDate = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter To Date (YYYY-MM-DD)");
        Date toDate = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter Type");
        String type = sc.nextLine();
        LOGGER.info("Enter Account ID");
        int accountId = sc.nextInt();
        return cs.create(id, fromDate, toDate, type, accountId);
    }

    private void save() {
        cs.save(create());
    }

    private void insert() {
        cs.insert(create());
    }

    private void update() {
        cs.update(create());
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        cs.delete(id);
    }
}
