package com.taxi.service.serviceImpl;


import com.taxi.service.utils.ConnectionHolder;
import com.taxi.service.utils.DataBaseUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandlerImpl {

    public static void execute(Transaction transaction) {
        try (Connection connection = getConnection()) {
            try {
                ConnectionHolder.setLocalConnection(connection);
                transaction.doTransaction();
            } catch (Exception e) {
                e.printStackTrace();
                rollBack(connection);
            } finally {
                commit(connection);
                ConnectionHolder.removeLocalConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void rollBack(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void commit(Connection connection) {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection getConnection() {
        try {
            Connection connection = DataBaseUtil.connectionPool.getConnection();
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
