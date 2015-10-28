package com.jean.taxi.serviceImpl;


import com.jean.taxi.utils.ConnectionHolder;
import com.jean.taxi.utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandlerImpl {

    private static DataSource dataSource = DataBaseUtil.connectionPool;

    public static void setDataSource(DataSource newDataSource) {
        dataSource = newDataSource;
    }

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
            Connection connection = dataSource.getConnection();
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
