package org.TODO.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.TODO.domain.User;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoUserRepository implements UserRepository {

    private final MongoCollection<Document> collection;

    public MongoUserRepository(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public User create(User user) {
        Document document = user.convertToDocument();
        collection.insertOne(document);
        return user;
    }


    @Override
    public List<User> findAll() {
        FindIterable<Document> findIterable = collection.find();
        List<User> users = new ArrayList<>();
        for(Document doc : findIterable){
            users.add(documentToUser(doc));
        }
        return users;
    }

    @Override
    public User update(User user, String queryID) {
        Document query = new Document("User ID", queryID);
        Document update = user.convertToDocument();
        collection.replaceOne(query, update);
        return documentToUser(update);
    }

    @Override
    public void delete(String userId) {
        collection.deleteOne(new Document("User ID", userId));
    }

    private User documentToUser(Document document) {
        String userId = document.getString("User ID");
        String userName = document.getString("User Name");
        User user = new User(userName);
        user.setId(userId);
        return user;
    }

}
