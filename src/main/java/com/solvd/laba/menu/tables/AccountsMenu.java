package com.solvd.laba.menu.tables;

import com.solvd.laba.services.AccountsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class AccountsMenu {

    private static final Logger LOGGER = LogManager.getLogger(AccountsMenu.class);
    private final Scanner sc;
    private final AccountsService as;

    public AccountsMenu() {
        this.as = new AccountsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Accounts");
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
