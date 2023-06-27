package org.TODO;

import org.TODO.utility.InputHandler;
import org.TODO.utility.MongoDbConnection;

public class Menus {
    MongoDbConnection connection;
    Facade facade;
    public Menus (MongoDbConnection connection){
        this.connection = connection;
        facade = new Facade(connection);
    }

    public void mainMenu(){
        boolean isRunning = true;
        InputHandler input = new InputHandler();

        System.out.println("Welcome to TODO");

        while(isRunning){
            System.out.println("""
                    
                    1. Manage Todo
                    2. Manage User
                    3. Quit  
                        
                    """);
            switch(input.getIntegerInput()){
                case 1 ->todoMenu();
                case 2 -> userMenu();
                case 3 -> isRunning = false;
                default -> System.out.println("Incorrect input, please try again.");
            }
        }
    }

    public void todoMenu(){
        boolean isRunning = true;

        InputHandler input = new InputHandler();

        while (isRunning){

            System.out.println("""
                    
                    1. Add Todo
                    2. View Todos
                    3. View Todos from specified user
                    4. Update Todo
                    5. Delete Todo
                    6. Exit Todo Menu    
                          
                    """);

            switch (input.getIntegerInput()){
                case 1 -> facade.createTodo();
                case 2 -> facade.viewTodos();
                case 3 -> facade.viewTodosFromSpecifiedUser();
                case 4 -> facade.updateTodo();
                case 5 -> facade.deleteTodo();
                case 6 -> isRunning = false;
                default -> System.out.println("Incorrect input, please try again");
            }
        }
    }
    public void userMenu(){
        boolean isRunning = true;

        InputHandler input = new InputHandler();

        while (isRunning){

            System.out.println("""
                    
                    1. Add User
                    2. View Users
                    3. Update User
                    4. Delete User
                    5. Exit Todo Menu     
                         
                    """);

            switch (input.getIntegerInput()){
                case 1 -> facade.createUser();
                case 2 -> facade.viewUsers();
                case 3 -> facade.updateUser();
                case 4 -> facade.deleteUser();
                case 5 -> isRunning = false;
                default -> System.out.println("Incorrect input, please try again");
            }
        }
    }




}
