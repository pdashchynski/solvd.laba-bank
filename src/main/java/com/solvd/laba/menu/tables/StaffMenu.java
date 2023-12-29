package com.solvd.laba.menu.tables;

import com.solvd.laba.services.StaffService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class StaffMenu {

    private static final Logger LOGGER = LogManager.getLogger(StaffMenu.class);
    private final Scanner sc;
    private final StaffService ss;

    public StaffMenu() {
        this.ss = new StaffService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Staff");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    ss.get();
                    break;
                case 2:
                    ss.getAll();
                    break;
                case 3:
                    ss.save();
                    break;
                case 4:
                    ss.insert();
                    break;
                case 5:
                    ss.update();
                    break;
                case 6:
                    ss.delete();
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
