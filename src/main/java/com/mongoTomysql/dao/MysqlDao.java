package com.mongoTomysql.dao;

import com.mongoTomysql.connection.MysqlConnection;
import com.mongoTomysql.entity.TbbBuddyGroup;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ankit on 8/7/16.
 */
public class MysqlDao {


    public void updateTbbGroups(List<TbbBuddyGroup> tbbBuddyGroups) {
        List<String> buddyGroupInsertsQueries = tbbBuddyGroups.stream().map(TbbBuddyGroup::getUpdator).collect(Collectors.toList());


        try (Connection connection = MysqlConnection.getConnection();
             Statement statement = connection.createStatement()
        ) {

            System.out.println("Adding queries to batch");

            buddyGroupInsertsQueries.stream().forEach(s -> {
                        System.out.println(s);

                        try {
                            statement.addBatch(s);

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            );
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
