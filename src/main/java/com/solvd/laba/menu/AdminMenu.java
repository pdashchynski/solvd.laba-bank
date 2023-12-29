package com.solvd.laba.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AdminMenu {

    private static final Logger LOGGER = LogManager.getLogger(AdminMenu.class);
    private final Scanner sc;

    public AdminMenu() {
        this.sc = new Scanner(System.in);
    }

    public void display() {
        loginMenu();
        loggedInMenu();
    }

    private void loginMenu() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            LOGGER.info("Admin Login Menu");
            LOGGER.info("1 - Log In");
            LOGGER.info("0 - Quit");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    login();
                    break;
                case 0:
                    isExit = true;
                    LOGGER.info("Exiting to Main Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Menu Input");
                    break;
            }
        }
    }

    private void loggedInMenu() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            LOGGER.info("Admin Menu");
            LOGGER.info("1 - Bank Operations");
            LOGGER.info("2 - Show Tables");
            LOGGER.info("0 - Quit");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    operationsSubMenu();
                    break;
                case 2:
                    tablesSubMenu();
                    break;
                case 0:
                    isExit = true;
                    LOGGER.info("Exiting to Main Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Menu Input");
                    break;
            }
        }
    }

    private void operationsSubMenu() {}

    private void tablesSubMenu() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            LOGGER.info("Tables Menu");
            LOGGER.info("1 - Accounts");
            LOGGER.info("2 - Addresses");
            LOGGER.info("3 - Cards");
            LOGGER.info("4 - Clients");
            LOGGER.info("5 - Departments");
            LOGGER.info("6 - Passports");
            LOGGER.info("7 - Persons");
            LOGGER.info("8 - Services");
            LOGGER.info("9 - Staff");
            LOGGER.info("10 - Transactions");
            LOGGER.info("0 - Quit");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    ;
                    break;
                case 2:
                    tablesSubMenu();
                    break;
                case 3:
                    tablesSubMenu();
                    break;
                case 4:
                    tablesSubMenu();
                    break;
                case 5:
                    tablesSubMenu();
                    break;
                case 6:
                    tablesSubMenu();
                    break;
                case 7:
                    tablesSubMenu();
                    break;
                case 8:
                    tablesSubMenu();
                    break;
                case 9:
                    tablesSubMenu();
                    break;
                case 10:
                    tablesSubMenu();
                    break;
                case 0:
                    isExit = true;
                    LOGGER.info("Exiting to Main Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Menu Input");
                    break;
            }
        }
    }
}
