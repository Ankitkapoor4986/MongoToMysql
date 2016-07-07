package com.mongoTomysql.dao;

import com.mongoTomysql.connection.MongoConnection;
import com.mongoTomysql.entity.TbbBuddyGroup;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Collection;

/**
 * Created by ankit on 6/7/16.
 */
public class MongoDao {

    private static final String LPORTAL = "lportal";
    private static final String COLLECTION_TBB_BUDDY_GROUP = "tbb_buddy_group";

    TbbBuddyGroup getTbbBuddyGroupWithMinusOneGroupId() {
        TbbBuddyGroup group = new TbbBuddyGroup();
        MongoConnection connection = new MongoConnection();
        try {
            MongoClient mongoClient = connection.getConnection();
            DB db = mongoClient.getDB(LPORTAL);
            System.out.print(db);
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("groupId", 0);
            DBCollection dbCollection = db.getCollection(COLLECTION_TBB_BUDDY_GROUP);
            DBObject dbObject = dbCollection.findOne();
            group.setUserId((Long) dbObject.get("userId"));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return group;
    }

    public static void main(String[] arStrings) {
        MongoDao mongoDao = new MongoDao();
        mongoDao.getTbbBuddyGroupWithMinusOneGroupId();
    }
}


