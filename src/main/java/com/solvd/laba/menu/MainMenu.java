package com.solvd.laba.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.solvd.laba.services.SwitcherService.*;

public final class MainMenu {

    private static final Logger LOGGER = LogManager.getLogger(MainMenu.class);
    private final Scanner sc;
    private static final AdminMenu AM = new AdminMenu();
    private static final UserMenu UM = new UserMenu();

    public MainMenu() {
        sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            LOGGER.info("Main Menu " + getCurrentAPI());
            LOGGER.info("1 - Enter Admin Menu");
            LOGGER.info("2 - Enter User Menu");
            LOGGER.info("3 - Switch to " + getAltAPI());
            LOGGER.info("0 - Quit");
            input = sc.nextInt();

            switch (input) {
                case 1 -> AM.display();
                case 2 -> UM.display();
                case 3 -> switchAPI();
                case 0 -> {
                    isExit = true;
                    LOGGER.info("Peace");
                }
                default -> LOGGER.info("Incorrect Input");
            }
        }
    }
}
