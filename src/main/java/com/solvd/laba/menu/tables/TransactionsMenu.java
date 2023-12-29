package com.solvd.laba.menu.tables;

import com.solvd.laba.database.model.Staff;
import com.solvd.laba.database.model.Transactions;
import com.solvd.laba.services.CardsService;
import com.solvd.laba.services.TransactionsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Scanner;

public class TransactionsMenu {

    private static final Logger LOGGER = LogManager.getLogger(TransactionsMenu.class);
    private final Scanner sc;
    private final TransactionsService ts;

    public TransactionsMenu() {
        this.ts = new TransactionsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Transactions");
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
        LOGGER.info(ts.get(id).toString());
    }

    private void printAll() {
        LOGGER.info(ts.getAll().toString());
    }

    private Transactions create() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter Type");
        String type = sc.nextLine();
        LOGGER.info("Enter Amount");
        int amount = sc.nextInt();
        LOGGER.info("Enter Currency");
        String currency = sc.nextLine();
        LOGGER.info("Enter Date Hired (YYYY-MM-DD HH:MI:SS)");
        Timestamp dateTime = Timestamp.valueOf(sc.nextLine());
        LOGGER.info("Enter Staff ID");
        int staffId = sc.nextInt();
        LOGGER.info("Enter Service ID");
        int serviceId = sc.nextInt();
        return ts.create(id, type, amount, currency, dateTime, staffId, serviceId);
    }

    private void save() {
        ts.save(create());
    }

    private void insert() {
        ts.insert(create());
    }

    private void update() {
        ts.update(create());
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        ts.delete(id);
    }
}
