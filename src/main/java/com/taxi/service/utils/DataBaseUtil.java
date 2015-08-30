package com.taxi.service.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * Connection jdbc class
 */
public class DataBaseUtil {

    private static Connection connection = null;

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(DataBaseUtil.class.getClassLoader()
                    .getResourceAsStream("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection openConnection() {
        try {
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}