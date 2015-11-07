package com.jean.taxi.serviceImpl;


import com.jean.taxi.exception.DaoException;
import com.jean.taxi.exception.ServiceException;
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

    public static void execute(Transaction transaction) throws ServiceException {
        try (Connection connection = getConnection()) {
            try {
                ConnectionHolder.setLocalConnection(connection);
                transaction.doTransaction();
            } catch (DaoException e) {
                rollBack(connection);
                throw new ServiceException("Can't commit transaction in transaction handler.", e);
            } finally {
                commit(connection);
                ConnectionHolder.removeLocalConnection();
            }
        } catch (SQLException e) {
            throw new ServiceException("Transaction not done!", e);
        }
    }

    public static void executeTest(Transaction transaction) {
        try (Connection connection = getConnection()) {
            try {
                ConnectionHolder.setLocalConnection(connection);
                transaction.doTransaction();
            } catch (DaoException e) {
                rollBack(connection);
                e.printStackTrace();
            } finally {
                rollBack(connection);
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
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
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
