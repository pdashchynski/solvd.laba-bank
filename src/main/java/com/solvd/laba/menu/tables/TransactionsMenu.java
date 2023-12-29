package com.solvd.laba.menu.tables;

import com.solvd.laba.services.CardsService;
import com.solvd.laba.services.TransactionsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class TransactionsMenu {

    private static final Logger LOGGER = LogManager.getLogger(TransactionsMenu.class);
    private final Scanner sc;
    private final TransactionsService ts;

    public TransactionsMenu() {
        this.ts = new TransactionsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Transactions");
            input = sc.nextInt();

            switch (input) {
                case 1:
                    ts.get();
                    break;
                case 2:
                    ts.getAll();
                    break;
                case 3:
                    ts.save();
                    break;
                case 4:
                    ts.insert();
                    break;
                case 5:
                    ts.update();
                    break;
                case 6:
                    ts.delete();
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
