package com.jean.taxi.serviceImpl;


import com.jean.taxi.exception.DaoException;

public abstract class Transaction<T> {
    public abstract void doTransaction() throws DaoException;
}
