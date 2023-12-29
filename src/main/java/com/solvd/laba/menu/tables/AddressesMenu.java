package com.solvd.laba.menu.tables;

import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.services.AddressesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Scanner;

public class AddressesMenu {

    private static final Logger LOGGER = LogManager.getLogger(AddressesMenu.class);
    private final Scanner sc;
    private final AddressesService as;

    public AddressesMenu() {
        this.as = new AddressesService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Addresses");
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

    private Addresses create() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter Country");
        String country = sc.nextLine();
        LOGGER.info("Enter City");
        String city = sc.nextLine();
        LOGGER.info("Enter Postal Code");
        String postalCode = sc.nextLine();
        return as.create(id, country, city, postalCode);
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
