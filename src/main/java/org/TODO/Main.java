package org.TODO;

import org.TODO.utility.InputHandler;
import org.TODO.utility.MongoDbConnection;

public class Main {
    public static void main(String[] args) {
        MongoDbConnection connection = new MongoDbConnection(new InputHandler());
        Menus menus = new Menus(connection);
        menus.mainMenu();
    }
}