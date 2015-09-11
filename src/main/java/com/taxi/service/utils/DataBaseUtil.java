package com.taxi.service.utils;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseUtil {

    public static final DataSource connectionPool = getConnectionPoolInstance();

    public static DataSource getConnectionPoolInstance() {
        try {
            Context envContext = (Context) context.lookup("java:comp/env");
            return (DataSource) envContext.lookup("jdbc/order_board");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return connectionPool;
    }
}