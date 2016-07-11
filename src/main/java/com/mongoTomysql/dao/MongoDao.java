package com.mongoTomysql.dao;

import com.mongoTomysql.connection.MongoConnection;
import com.mongoTomysql.entity.TbbBuddyGroup;
import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit on 6/7/16.
 */
public class MongoDao {


    private static final String COLLECTION_TBB_BUDDY_GROUP = "tbb_buddy_group";
    public static final String LOC = "loc";
    public static final String USER_ID = "userId";
    public static final String GROUP_ID = "groupId";
    public static final String LAT = "LAT";
    public static final String LNG = "LNG";
    public static final int RADIUS = 1;
    public static final String $_SET = "$set";
    private static final String BUDDY_GROUP_ID="buddyGroupId";

    public List<Long> getUserIdTOupdate(TbbBuddyGroup tbbBuddyGroup) {
        List<Long> userIds = new ArrayList<Long>();
        MongoConnection connection = new MongoConnection();
        try {
            DB db = connection.getLportalDB();
            DBCollection tbbBuddyGroupCollection = db.getCollection(COLLECTION_TBB_BUDDY_GROUP);
            List circle = new ArrayList();
            circle.add(new double[]{tbbBuddyGroup.getLat(), tbbBuddyGroup.getLng()}); // Centre of circle
            circle.add(RADIUS);
            BasicDBObject query = new BasicDBObject(LOC, new BasicDBObject("$within",
                    new BasicDBObject("$center", circle)));
            BasicDBObject minusOneEqualQuery = new BasicDBObject();
            minusOneEqualQuery.put(GROUP_ID, -1);

            List<BasicDBObject> basicDBObjects=new ArrayList<BasicDBObject>();
            basicDBObjects.add(query);
            basicDBObjects.add(minusOneEqualQuery);

            BasicDBObject andQuery=new BasicDBObject();
            andQuery.put("$and",basicDBObjects);
            System.out.println("***************************************");
            System.out.println(andQuery);
            System.out.println("***************************************");
            DBCursor cursor = tbbBuddyGroupCollection.find(andQuery);
            while (cursor.hasNext()) {
                DBObject result = cursor.next();
                System.out.println(result.get(USER_ID) + "--->" + result.get(LOC));
                userIds.add(Long.parseLong(result.get(USER_ID).toString()));

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return userIds;
    }


    public TbbBuddyGroup getTbbBuddyGroupWithMinusOneGroupId() {
        TbbBuddyGroup group= null;
        MongoConnection connection = new MongoConnection();
        try {

            DB db = connection.getLportalDB();

            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put(GROUP_ID, -1.0);
            DBCollection tbbBuddyGroupCollection = db.getCollection(COLLECTION_TBB_BUDDY_GROUP);
            DBObject dbObject = tbbBuddyGroupCollection.findOne(whereQuery);
            System.out.println(dbObject);
            if (dbObject != null) {
                group = new TbbBuddyGroup();
                group.setUserId(Long.parseLong(dbObject.get(USER_ID).toString()));
                group.setLat((Double) dbObject.get(LAT));
                group.setLng((Double) dbObject.get(LNG));
                group.setGroupId(Math.round((Double) dbObject.get(GROUP_ID)));
                System.out.println("**************************");
                System.out.println(group);
                System.out.println("**************************");
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return group;
    }


    public List<TbbBuddyGroup> getAllRecords(){
        List<TbbBuddyGroup> tbbBuddyGroups=new ArrayList<TbbBuddyGroup>();
        MongoConnection connection=new MongoConnection();
        try {
            DB db=connection.getLportalDB();
            DBCollection tbbBuddyGroupCollection = db.getCollection(COLLECTION_TBB_BUDDY_GROUP);
            DBCursor dbCursor=tbbBuddyGroupCollection.find();
            while (dbCursor.hasNext()) {
                TbbBuddyGroup group=new TbbBuddyGroup();
                DBObject dbObject=dbCursor.next();
                group.setUserId(Long.parseLong(dbObject.get(USER_ID).toString()));
                group.setLng((Double) dbObject.get(LNG));
                group.setLat((Double) dbObject.get(LAT));
                group.setGroupId(Long.parseLong(dbObject.get(GROUP_ID).toString()));
                group.setBuddyGroupId(Long.valueOf(dbObject.get(BUDDY_GROUP_ID).toString()));
                System.out.println(group);
                tbbBuddyGroups.add(group);

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return tbbBuddyGroups;
    }






    public  void updateUsers(int count, List<Long> userIds) {


        MongoConnection connection = new MongoConnection();
        DB db = null;
        try {
            db = connection.getLportalDB();
            DBCollection tbbBuddyGroupCollection = db.getCollection(COLLECTION_TBB_BUDDY_GROUP);
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.append($_SET, new BasicDBObject().append(GROUP_ID, count));

            for (int i = 0; i < userIds.size(); i++) {

                BasicDBObject searchQuery = new BasicDBObject().append(USER_ID, userIds.get(i))
                        .append(GROUP_ID,-1);

                System.out.println(tbbBuddyGroupCollection.update(searchQuery,newDocument));
                System.out.println("NewDocument" +newDocument);
                System.out.println("SearchQuery"+searchQuery);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}


