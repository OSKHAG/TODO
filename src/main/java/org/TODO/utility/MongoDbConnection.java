package org.TODO.utility;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDbConnection {
    private String password = "";
    private String uriString = "mongodb+srv://user1:" + password + "@cluster0.zhcvtru.mongodb.net/?retryWrites=true&w=majority";
    private final InputHandler input;
    private MongoDatabase database;


    public MongoDbConnection(InputHandler input) {
        this.input = input;
    }

    public void login() {
        int maxAttempts = 5;
        boolean loop = false;

        while (!loop) {
            System.out.println("Enter Password: ");
            password = input.getStringInput();
            uriString = generateUriString(password);
            loop = testConnection(maxAttempts);
        }

        System.out.println("You have successfully logged in!");
    }

    public boolean testConnection(int maxAttempts) {
        int attempt = 1;

        while (attempt <= maxAttempts) {
            try (MongoClient client = createMongoClient()) {
                database = client.getDatabase("TODOdb");
                MongoIterable<String> collectionNames = database.listCollectionNames();
                return collectionNames.iterator().hasNext();
            } catch (MongoException e) {
                System.out.println("Incorrect password, please try again");
                attempt++;
            }
        }

        return false;
    }


    private MongoClient createMongoClient() {
        return MongoClients.create(uriString);
    }

    private String generateUriString(String password) {
        return "mongodb+srv://user1:" + password + "@cluster0.zhcvtru.mongodb.net";
    }

    public MongoDatabase getDatabase() {
        MongoClient client = createMongoClient();
        database = client.getDatabase("TODOdb");
        return database;
    }
    public List<Document> getCollection(String type){
        MongoCollection<Document> collection = getDatabase().getCollection(type);
        List<Document> documents = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                documents.add(cursor.next());
            }
        }
        return documents;
    }

}
