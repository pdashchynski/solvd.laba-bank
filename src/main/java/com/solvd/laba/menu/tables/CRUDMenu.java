package com.solvd.laba.menu.tables;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CRUDMenu {

    private static final Logger LOGGER = LogManager.getLogger(CRUDMenu.class);

    public static void display(String tableName) {
        LOGGER.info(tableName + " CRUD Menu");
        LOGGER.info("1 - Print");
        LOGGER.info("2 - Print All");
        LOGGER.info("3 - Save");
        LOGGER.info("4 - Insert");
        LOGGER.info("5 - Update");
        LOGGER.info("6 - Delete");
        LOGGER.info("0 - Quit");
    }
}
