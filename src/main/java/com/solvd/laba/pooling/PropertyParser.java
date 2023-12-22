package com.solvd.laba.pooling;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertyParser {

    private static final String PATH = "src/main/resources/config.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(new FileInputStream(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String getDriver() {
        return PROPERTIES.getProperty("driver");
    }

    static String getBaseUrl() {
        return PROPERTIES.getProperty("baseUrl");
    }

    static String getUrl() {
        return PROPERTIES.getProperty("url");
    }

    static String getUsername() {
        return PROPERTIES.getProperty("username");
    }

    static String getPassword() {
        return PROPERTIES.getProperty("password");
    }
}
