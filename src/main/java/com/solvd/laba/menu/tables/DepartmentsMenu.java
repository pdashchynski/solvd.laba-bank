package com.solvd.laba.menu.tables;

import com.solvd.laba.services.DepartmentsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class DepartmentsMenu {

    private static final Logger LOGGER = LogManager.getLogger(DepartmentsMenu.class);
    private final Scanner sc;
    private final DepartmentsService ds;

    public DepartmentsMenu() {
        this.ds = new DepartmentsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Departments");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    ds.get();
                    break;
                case 2:
                    ds.getAll();
                    break;
                case 3:
                    ds.save();
                    break;
                case 4:
                    ds.insert();
                    break;
                case 5:
                    ds.update();
                    break;
                case 6:
                    ds.delete();
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
