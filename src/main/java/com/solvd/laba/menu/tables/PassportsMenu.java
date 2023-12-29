package com.solvd.laba.menu.tables;

import com.solvd.laba.services.PassportsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class PassportsMenu {

    private static final Logger LOGGER = LogManager.getLogger(PassportsMenu.class);
    private final Scanner sc;
    private final PassportsService ps;

    public PassportsMenu() {
        this.ps = new PassportsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Passports");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    ps.get();
                    break;
                case 2:
                    ps.getAll();
                    break;
                case 3:
                    ps.save();
                    break;
                case 4:
                    ps.insert();
                    break;
                case 5:
                    ps.update();
                    break;
                case 6:
                    ps.delete();
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
