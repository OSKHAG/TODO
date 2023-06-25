package org.TODO.repository;

import org.TODO.domain.Todo;

import java.util.List;

public interface TodoRepository {
    Todo create(Todo todo);
    List<Todo> findByUserId(String userId);
    List<Todo> findAll();
    Todo update(Todo todo, String queryID);
    void delete(String todoId);
}
