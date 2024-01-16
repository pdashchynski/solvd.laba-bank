package com.solvd.laba.menu;

import com.solvd.laba.menu.tables.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class AdminMenu {

    private static final Logger LOGGER = LogManager.getLogger(AdminMenu.class);
    private final Scanner sc;
    private static final Properties PROPERTIES = new Properties();
    private static final String PATH = "src/main/resources/config.properties";
    private final AccountsMenu accountsMenu = new AccountsMenu();
    private final AddressesMenu addressesMenu = new AddressesMenu();
    private final CardsMenu cardsMenu = new CardsMenu();
    private final ClientsMenu clientsMenu = new ClientsMenu();
    private final DepartmentsMenu departmentsMenu = new DepartmentsMenu();
    private final PassportsMenu passportsMenu = new PassportsMenu();
    private final PersonsMenu personsMenu = new PersonsMenu();
    private final ServicesMenu servicesMenu = new ServicesMenu();
    private final StaffMenu staffMenu = new StaffMenu();
    private final TransactionsMenu transactionsMenu = new TransactionsMenu();

    static {
        try {
            PROPERTIES.load(new FileInputStream(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AdminMenu() {
        this.sc = new Scanner(System.in);
    }

    public void display() {
        loginMenu();
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

    private void login() {
        String username = PROPERTIES.getProperty("username");
        String password = PROPERTIES.getProperty("password");
        boolean isExit = false;

        while (!isExit) {
            LOGGER.info("Please Enter the Username");
            String usernameInput = sc.nextLine();
            if (usernameInput.equals(username)) {
                LOGGER.info("Please Enter the Password");
                String passwordInput = sc.nextLine();
                if (passwordInput.equals(password)) {
                    loggedInMenu();
                    isExit = true;
                }
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

    private void operationsSubMenu() {
    }

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
                    accountsMenu.display();
                    break;
                case 2:
                    addressesMenu.display();
                    break;
                case 3:
                    cardsMenu.display();
                    break;
                case 4:
                    clientsMenu.display();
                    break;
                case 5:
                    departmentsMenu.display();
                    break;
                case 6:
                    passportsMenu.display();
                    break;
                case 7:
                    personsMenu.display();
                    break;
                case 8:
                    servicesMenu.display();
                    break;
                case 9:
                    staffMenu.display();
                    break;
                case 10:
                    transactionsMenu.display();
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
