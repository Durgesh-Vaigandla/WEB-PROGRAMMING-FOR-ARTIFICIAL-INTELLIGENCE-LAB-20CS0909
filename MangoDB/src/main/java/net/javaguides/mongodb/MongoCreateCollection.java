package net.javaguides.mongodb.collection;

import java.util.ArrayList;
import org.bson.Document;
import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

public class MongoCreateCollection {
    public static void main(String[] args) {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            var database = mongoClient.getDatabase("javaguides");
            MongoCollection<Document> collection = database.getCollection("users");

            // Clean up existing collection if it exists
            if (collectionExists(database, "users")) {
                try {
                    collection.drop();
                    System.out.println("Existing 'users' collection dropped.");
                } catch (Exception ex) {
                    System.err.println("Error dropping collection: " + ex.getMessage());
                    return;
                }
            }

            // Create new collection implicitly by inserting documents
            var docs = new ArrayList<Document>();
            
            // Use proper field names (without leading underscores)
            docs.add(new Document("_id", 1)
                .append("firstName", "Ramesh")
                .append("lastName", "Fadatare"));
                
            docs.add(new Document("_id", 2)
                .append("firstName", "Tony")
                .append("lastName", "Stark"));
                
            docs.add(new Document("_id", 3)
                .append("firstName", "Tom")
                .append("lastName", "Cruise"));
                
            docs.add(new Document("_id", 4)
                .append("firstName", "Amir")
                .append("lastName", "Khan"));
                
            docs.add(new Document("_id", 5)
                .append("firstName", "Umesh")
                .append("lastName", "Fadatare"));

            collection.insertMany(docs);
            System.out.println(docs.size() + " documents inserted successfully.");
            
        } catch (Exception e) {
            System.err.println("General error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean collectionExists(com.mongodb.client.MongoDatabase database, String collectionName) {
        return database.listCollectionNames()
            .into(new ArrayList<>())
            .contains(collectionName);
    }
}