package com.taxi.service.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseUtil {

    public static DataSource connectionPool;

    private static Connection connection = null;

    private static Properties properties = new Properties();

    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    static {
        try {
            properties.load(DataBaseUtil.class.getClassLoader()
                    .getResourceAsStream("database.properties"));
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            connectionPool = (DataSource) envContext.lookup("jdbc/order_board");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NamingException e) {
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