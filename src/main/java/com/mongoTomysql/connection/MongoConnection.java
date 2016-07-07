package com.mongoTomysql.connection;

import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by ankit on 6/7/16.
 */
public class MongoConnection {
    private static final String SERVER_PATH = "localhost";
    private static final int PORT=27017;
    public MongoClient getConnection() throws UnknownHostException {
       return new MongoClient(SERVER_PATH,PORT);
    }
}
