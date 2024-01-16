package com.solvd.laba.menu.tables;

import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.services.AccountsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Scanner;

public class AccountsMenu {

    private static final Logger LOGGER = LogManager.getLogger(AccountsMenu.class);
    private final Scanner sc;
    private final AccountsService as;

    public AccountsMenu() {
        this.as = new AccountsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Accounts");
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
        LOGGER.info(as.get(id).toString());
    }

    private void printAll() {
        LOGGER.info(as.getAll().toString());
    }

    private Accounts create() {
        boolean isExit = false;
        int input;
        Accounts account = null;
        while (!isExit) {
            LOGGER.info("1 - Enter From Console");
            LOGGER.info("2 - Parse XML(SAX)");
            LOGGER.info("3 - Parse XML(JAXB)");
            LOGGER.info("4 - Parse JSON");
            LOGGER.info("0 - Quit");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    account = createFromConsole();
                    break;
                case 2:
                    sax();
                    break;
                case 3:
                    jaxb();
                    break;
                case 4:
                    json();
                    break;
                case 0:
                    isExit = true;
                    LOGGER.info("Back to Accounts Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Menu Input");
                    break;
            }
        }
        return account;
    }

    private Accounts createFromConsole() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter From Date (YYYY-MM-DD)");
        Date fromDate = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter To Date (YYYY-MM-DD)");
        Date toDate = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter Balance");
        int balance = sc.nextInt();
        LOGGER.info("Enter Currency");
        String currency = sc.nextLine();
        LOGGER.info("Enter Client ID");
        int clientId = sc.nextInt();
        return as.create(id, fromDate, toDate, balance, currency, clientId);
    }

    private void save() {
        as.save(create());
    }

    private void insert() {
        as.insert(create());
    }

    private void update() {
        as.update(create());
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        as.delete(id);
    }
}
