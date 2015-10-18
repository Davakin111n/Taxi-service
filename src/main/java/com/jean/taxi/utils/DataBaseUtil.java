package com.jean.taxi.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseUtil {

    public static final DataSource connectionPool = getConnectionPoolInstance();

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
}
