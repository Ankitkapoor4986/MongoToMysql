package com.mongoTomysql.main;

import com.mongoTomysql.dao.MongoDao;
import com.mongoTomysql.dao.MysqlDao;
import com.mongoTomysql.entity.TbbBuddyGroup;

import java.util.List;

/**
 * Created by ankit on 8/7/16.
 */
public class MainClass {


    public static void main(String[] arStrings) {
        MongoDao mongoDao = new MongoDao();
//        TbbBuddyGroup tbbBuddyGroup=new TbbBuddyGroup();
//        int groupIdCount=0;
//        while(tbbBuddyGroup!=null) {
//
//            tbbBuddyGroup = mongoDao.getTbbBuddyGroupWithMinusOneGroupId();
//
//            if (tbbBuddyGroup != null) {
//                List<Long> userIds = mongoDao.getUserIdTOupdate(tbbBuddyGroup);
//                System.out.println(userIds);
//
//                groupIdCount++;
//                userIds.add(tbbBuddyGroup.getUserId());
//                mongoDao.updateUsers(groupIdCount,userIds);
//
//
//
//            }
//        }

       List<TbbBuddyGroup> tbbBuddyGroups= mongoDao.getAllRecords();
        MysqlDao mysqlDao=new MysqlDao();
        mysqlDao.updateTbbGroups(tbbBuddyGroups);
    }
}
