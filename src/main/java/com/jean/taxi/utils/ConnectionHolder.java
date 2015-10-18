package com.jean.taxi.utils;

import java.sql.Connection;

public class ConnectionHolder {
    private static final ThreadLocal<Connection> threadLocalHolder = new ThreadLocal<>();

    public final static Connection getLocalConnection() {
        return threadLocalHolder.get();
    }

    public final static void setLocalConnection(Connection connection) {
        threadLocalHolder.set(connection);
    }

    public final static void removeLocalConnection() {
        threadLocalHolder.remove();
    }
}
