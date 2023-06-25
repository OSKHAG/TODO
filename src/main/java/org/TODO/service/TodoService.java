package org.TODO.service;

import org.TODO.domain.Todo;
import org.TODO.repository.TodoRepository;

import java.util.List;

public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.create(todo);
    }

    public List<Todo> getTodosByUserId(String userId) {
        return todoRepository.findByUserId(userId);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(Todo todo, String queryID) {
        return todoRepository.update(todo, queryID);
    }

    public void deleteTodoById(String todoId) {
        todoRepository.delete(todoId);
    }
}
