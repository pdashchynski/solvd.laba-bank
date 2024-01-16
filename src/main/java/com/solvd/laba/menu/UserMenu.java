package com.solvd.laba.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UserMenu {

    private static final Logger LOGGER = LogManager.getLogger(UserMenu.class);
    private final Scanner sc;

    public UserMenu() {
        this.sc = new Scanner(System.in);
    }

    public void display() {
        loginMenu();
        loggedInMenu();
    }

    private void loginMenu() {}

    private void loggedInMenu() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            LOGGER.info("User Menu");
            LOGGER.info("1 - Current Balance");
            LOGGER.info("2 - Withdraw Money");
            LOGGER.info("3 - Top up Balance");
            LOGGER.info("4 - Get a Credit");
            LOGGER.info("5 - Pay off the Credit");
            LOGGER.info("0 - Back");
            input = sc.nextInt();

            switch (input) {
      /*          case 1:
                    showCurrentBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    topUpBalance();
                    break;
                case 4:
                    getCredit();
                    break;
                case 5:
                    payCredit();
                    break;*/
                case 0:
                    isExit = true;
                    LOGGER.info("Exiting to Main Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Input");
                    break;
            }
        }
    }

}
