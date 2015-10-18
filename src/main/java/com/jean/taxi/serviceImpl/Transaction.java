package com.jean.taxi.serviceImpl;


public abstract class Transaction<T> {
    public abstract void doTransaction() throws Exception;
}
