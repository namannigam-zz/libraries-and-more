package com.stackoverflow.nullpointer;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.Document;


public class Sample {

    public static void main(String[] args) {
        MongoDatabase database = MongoClients.create().getDatabase("");
        MongoCollection<Document> bookCollection = database.getCollection("");
        FindIterable<Document> books = bookCollection.find()
                .projection(Projections.include("_id", "title", "year"))
                .sort(Sorts.descending("title"));

        FindIterable<Document> book = bookCollection.find()
                .projection(Projections.include("_id", "title", "year"));

    }
}