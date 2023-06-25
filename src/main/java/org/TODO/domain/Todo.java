package org.TODO.domain;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Todo {
    private String todoId;
    private String description;
    private boolean done;
    private String userId;
    private String userName;

    public Todo(String description, User user) {
        this.todoId = new ObjectId().toString();
        this.description = description;
        this.done = false;
        this.userId = user.getId();
        this.userName = user.getName();
    }

    public String getTodoId() {
        return todoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUser(User user){
        setUserId(user.getId());
        setUserName(user.getName());
    }

    public Document convertToDocument(){
        return new Document().append("TODO ID", getTodoId())
                .append("Description", getDescription())
                .append("Status", isDone())
                .append("Assigned User", getUserName())
                .append("Assigned User ID", getUserId());
    }

    public void setTodoId(String id) {
        todoId = id;
    }
}
