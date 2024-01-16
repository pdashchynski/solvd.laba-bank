package com.solvd.laba;

import com.solvd.laba.menu.MainMenu;
import com.solvd.laba.pooling.ConnectionPool;

public class Executor {

    public static void main(String[] args) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        MainMenu mm = new MainMenu();
        mm.display();
    }
}
