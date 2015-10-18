package com.jean.taxi.service;

import com.jean.taxi.serviceImpl.Transaction;

import java.sql.Connection;

public interface TransactionHandler<T> {
    <T> T execute(Transaction<T> transaction);

    void rollBack(Connection connection);

    void commit(Connection connection);
}
