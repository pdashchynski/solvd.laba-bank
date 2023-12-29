package com.solvd.laba.menu.tables;

import com.solvd.laba.database.model.Passports;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.services.PersonsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Scanner;

public class PersonsMenu {

    private static final Logger LOGGER = LogManager.getLogger(PersonsMenu.class);
    private final Scanner sc;
    private final PersonsService ps;

    public PersonsMenu() {
        this.ps = new PersonsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Persons");
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

    private Persons create() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter First Name");
        String firstName = sc.nextLine();
        LOGGER.info("Enter Middle Name");
        String middleName = sc.nextLine();
        LOGGER.info("Enter Last Name");
        String lastName = sc.nextLine();
        LOGGER.info("Enter Age");
        short age = sc.nextShort();
        LOGGER.info("Enter From Date (YYYY-MM-DD)");
        Date dateOfBirth = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter Gender");
        String gender = sc.nextLine();
        LOGGER.info("Enter Address ID");
        int addressId = sc.nextInt();
        return ps.create(id, firstName, middleName, lastName, age,
                        dateOfBirth, gender, addressId);
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
