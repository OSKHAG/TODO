package org.TODO;

import org.TODO.domain.Todo;
import org.TODO.domain.User;
import org.TODO.repository.MongoTodoRepository;
import org.TODO.repository.MongoUserRepository;
import org.TODO.service.TodoService;
import org.TODO.service.UserService;
import org.TODO.utility.InputHandler;
import org.TODO.utility.MongoDbConnection;
import org.TODO.utility.TodoFactory;
import org.TODO.utility.UserFactory;

import java.util.List;

public class Facade {
    private final MongoUserRepository userRepo;
    private final MongoTodoRepository todoRepo;
    private final TodoService todoService;
    private final UserService userService;
    private final InputHandler input;

    public Facade(MongoDbConnection connection) {
        userRepo = new MongoUserRepository(connection.getUserCollection());
        todoRepo = new MongoTodoRepository(connection.getTodoCollection());
        userService = new UserService(userRepo);
        todoService = new TodoService(todoRepo);
        input = new InputHandler();
    }

    public void createTodo() {
        TodoFactory todoFactory = new TodoFactory();
        System.out.println("Enter TODO description");
        String description = input.getStringInput();
        System.out.println("Choose desired User");
        User user = chooseUser();
        todoService.createTodo(todoFactory.createTodo(description, user));
    }

    public void viewTodos() {
        printTodos(todoService.getAllTodos());
    }

    public void viewTodosFromSpecifiedUser(){
        System.out.println("Please choose a user to view assigned Todos");
        User user = chooseUser();
        List<Todo> todoList = todoRepo.findByUserId(user.getId());
        printTodos(todoList);
    }

    public void updateTodo() {
        List<Todo> todos = todoService.getAllTodos();
        printTodos(todos);
        System.out.println("Choose which todo to update");
        Todo updatedTodo = todos.get(input.getIntegerInput());
        String todoId = updatedTodo.getTodoId();
        updatedTodo = updateTodoDetails(updatedTodo);
        todoService.updateTodo(updatedTodo, todoId);
    }

    private Todo updateTodoDetails(Todo todo) {
        boolean isRunning = true;
        while (isRunning) {
            todo.printTodo();
            System.out.println("""
                    1. Update Description
                    2. Update Assigned User
                    3. Set Todo to Done
                    4. Confirm Changes
                    """);
            switch (input.getIntegerInput()) {
                case 1 -> {
                    System.out.println("Input new description: ");
                    todo.setDescription(input.getStringInput());
                }
                case 2 -> {
                    System.out.println("Choose desired User");
                    todo.setUser(chooseUser());
                }
                case 3 -> {
                    System.out.println("Todo is done");
                    todo.setDone(true);
                }
                case 4 -> isRunning = false;
                default -> System.out.println("Incorrect input, please try again");
            }
        }
        return todo;
    }

    public void deleteTodo() {
        List<Todo> todoList = todoService.getAllTodos();
        printTodos(todoList);
        System.out.println("Choose which Todo to delete: ");
        int choice = input.getIntegerInput();
        System.out.println(todoList.get(choice).getTodoId());
        todoService.deleteTodoById(todoList.get(choice).getTodoId());
    }

    private void printTodos(List<Todo> list) {
        int count = 0;
        for (Todo todo : list) {
            System.out.println(count + ".\nID : " + todo.getTodoId() +
                    "\nDescription : " + todo.getDescription() +
                    "\nAssigned User : " + todo.getUserName() +
                    "\nDone : " + todo.isDone());
            count++;
        }
    }

    public void createUser() {
        UserFactory userFactory = new UserFactory();
        System.out.println("Enter Username");
        String userName = input.getStringInput();
        userService.createUser(userFactory.createUser(userName));
    }

    private User chooseUser() {
        List<User> userList = userService.getAllUsers();
        printUsers(userList);
        int choice = input.getIntegerInput();
        return userList.get(choice);
    }

    private void printUsers(List<User> list) {
        int count = 0;
        for (User user : list) {
            System.out.println(count + " - " + user.getName());
            count++;
        }
    }

    public void viewUsers() {
        printUsers(userService.getAllUsers());
    }

    public void updateUser() {
        List<User> userList = userService.getAllUsers();
        printUsers(userList);
        System.out.println("Choose which user to update: ");
        int choice = input.getIntegerInput();
        User updatedUser = userList.get(choice);
        System.out.println("Input new name: ");
        String newName = input.getStringInput();
        updatedUser.setName(newName);
        userService.updateUser(updatedUser, updatedUser.getId());
    }

    public void deleteUser() {
        List<User> userList = userService.getAllUsers();
        printUsers(userList);
        System.out.println("Choose which user to delete: ");
        int choice = input.getIntegerInput();
        userService.deleteUserById(userList.get(choice).getId());
    }
}
