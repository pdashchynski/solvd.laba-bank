package com.solvd.laba.menu.tables;

import com.solvd.laba.database.model.Cards;
import com.solvd.laba.database.model.Clients;
import com.solvd.laba.services.ClientsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.Scanner;

public class ClientsMenu {

    private static final Logger LOGGER = LogManager.getLogger(ClientsMenu.class);
    private final Scanner sc;
    private final ClientsService cs;

    public ClientsMenu() {
        this.cs = new ClientsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Clients");
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

    private Clients create() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter Person ID");
        int personId = sc.nextInt();
        LOGGER.info("Enter Status");
        String status = sc.nextLine();
        return cs.create(id, personId, status);
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
