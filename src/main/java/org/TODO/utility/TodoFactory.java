package org.TODO.utility;

import org.TODO.domain.Todo;
import org.TODO.domain.User;


public class TodoFactory {
    public Todo createTodo(String description, User user){
        return new Todo(description, user);
    }
}
