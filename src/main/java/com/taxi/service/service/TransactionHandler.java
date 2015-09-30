package com.taxi.service.service;

import com.taxi.service.serviceImpl.Transaction;

import java.sql.Connection;

public interface TransactionHandler<T> {
    <T> T execute(Transaction<T> transaction);

    void rollBack(Connection connection);

    void commit(Connection connection);
}
