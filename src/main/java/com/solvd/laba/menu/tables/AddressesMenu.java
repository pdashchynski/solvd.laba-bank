package com.solvd.laba.menu.tables;

import com.solvd.laba.services.AddressesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
                    as.get();
                    break;
                case 2:
                    as.getAll();
                    break;
                case 3:
                    as.save();
                    break;
                case 4:
                    as.insert();
                    break;
                case 5:
                    as.update();
                    break;
                case 6:
                    as.delete();
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
}
