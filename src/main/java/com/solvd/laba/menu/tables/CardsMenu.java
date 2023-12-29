package com.solvd.laba.menu.tables;

import com.solvd.laba.services.CardsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class CardsMenu {

    private static final Logger LOGGER = LogManager.getLogger(CardsMenu.class);
    private final Scanner sc;
    private final CardsService cs;

    public CardsMenu() {
        this.cs = new CardsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Cards");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    cs.get();
                    break;
                case 2:
                    cs.getAll();
                    break;
                case 3:
                    cs.save();
                    break;
                case 4:
                    cs.insert();
                    break;
                case 5:
                    cs.update();
                    break;
                case 6:
                    cs.delete();
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
