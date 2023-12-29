package com.solvd.laba.services;

public class SwitcherService {
    private static String currentAPI = "JDBC";
    private static String altAPI = "MyBatis";
    private static boolean jdbcOn = true;
    private static String dbName;

    public static void switchAPI() {
        if (isJdbcOn()) {
            setCurrentAPI("MyBatis");
            setAltAPI("JDBC");
            setJdbcOn(false);
        } else {
            setCurrentAPI("JDBC");
            setAltAPI("MyBatis");
            setJdbcOn(true);
        }
    }

    public static void changeDB(String name) {
        setDbName(name);
    }

    public static String getCurrentAPI() {
        return currentAPI;
    }

    private static void setCurrentAPI(String currentAPI) {
        SwitcherService.currentAPI = currentAPI;
    }

    public static String getAltAPI() {
        return altAPI;
    }

    private static void setAltAPI(String altAPI) {
        SwitcherService.altAPI = altAPI;
    }

    public static boolean isJdbcOn() {
        return jdbcOn;
    }

    private static void setJdbcOn(boolean jdbcOn) {
        SwitcherService.jdbcOn = jdbcOn;
    }

    public static String getDbName() {
        return dbName;
    }

    private static void setDbName(String dbName) {
        SwitcherService.dbName = dbName;
    }
}
