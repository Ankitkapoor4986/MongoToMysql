package com.mongoTomysql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ankit on 8/7/16.
 */
public class MysqlConnection {
    public static final String JDBC_MYSQL_LOCALHOST = "jdbc:mysql://localhost:3306/";
    public static final String ROOT_USERNAME_AND_PASSWORD = "root";
    public static final String LPORTAL_DB="lportal_gen";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    JDBC_MYSQL_LOCALHOST.concat(LPORTAL_DB),
                    ROOT_USERNAME_AND_PASSWORD,
                    ROOT_USERNAME_AND_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
