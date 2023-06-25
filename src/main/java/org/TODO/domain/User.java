package org.TODO.domain;

import org.bson.Document;
import org.bson.types.ObjectId;

public class User {

    private String id;
    private String name;

    public User(String name) {
        this.id = new ObjectId().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Document convertToDocument(){
        return new Document().append("User ID", getId())
                .append("User Name", getName());
    }
    public void setId(String id){
        this.id = id;
    }


}
