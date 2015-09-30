package com.taxi.service.utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBaseUtil {

    public static final DataSource connectionPool = getMySQLDataSource();

    public static DataSource getConnectionPoolInstance() {
        try {
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            return (DataSource) envContext.lookup("jdbc/jean_taxi_service");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return connectionPool;
    }

    public static DataSource getMySQLDataSource() {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        MysqlDataSource mySqlDataSource = null;
        try {
            fileInputStream = new FileInputStream("database.properties");
            properties.load(fileInputStream);
            mySqlDataSource = new MysqlDataSource();
            mySqlDataSource.setURL(properties.getProperty("url"));
            mySqlDataSource.setUser(properties.getProperty("username"));
            mySqlDataSource.setPassword(properties.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mySqlDataSource;
    }
}