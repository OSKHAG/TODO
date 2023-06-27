package org.TODO.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.TODO.domain.Todo;
import org.TODO.domain.User;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;

public class MongoTodoRepository implements TodoRepository {
    private final MongoCollection<Document> collection;

    public MongoTodoRepository(MongoCollection<Document> collection) {
        this.collection = collection;
    }
    @Override
    public Todo create(Todo todo) {
        Document document = todo.convertToDocument();
        collection.insertOne(document);
        return todo;
    }

    @Override
    public List<Todo> findByUserId(String userId) {
        Document query = new Document("Assigned User ID", userId);
        FindIterable<Document> findIterable = collection.find(query);
        List<Todo> todos = new ArrayList<>();
        for(Document doc : findIterable){
            todos.add(documentToTodo(doc));
        }
        return todos;
    }

    @Override
    public List<Todo> findAll() {
        FindIterable<Document> findIterable = collection.find();
        List<Todo> todos = new ArrayList<>();
        for(Document doc : findIterable){
            todos.add(documentToTodo(doc));
        }
        return todos;
    }


    @Override
    public Todo update(Todo todo, String queryID) {
        Document query = new Document("TODO ID", queryID);
        Document update = todo.convertToDocument();
        collection.replaceOne(query, update);
        return todo;
    }

    @Override
    public void delete(String todoId) {
        collection.deleteOne(new Document("TODO ID", todoId));
    }

    private Todo documentToTodo(Document document) {
        String todoID = document.getString("TODO ID");
        String description = document.getString("Description");
        boolean status = document.getBoolean("Status");
        String userId = document.getString("Assigned User ID");
        String userName = document.getString("Assigned User");

        User user = new User(userId);
        Todo todo = new Todo(description, user);
        todo.setTodoId(todoID);
        todo.setDone(status);
        todo.setUserId(userId);
        todo.setUserName(userName);

        return todo;
    }
}
