package com.jean.taxi.dao;

import com.jean.taxi.entity.Identifier;
import com.jean.taxi.exception.DaoException;

import java.util.List;

public interface GenericDao<T extends Identifier> {

    T get(Long id) throws DaoException;

    boolean isExists(Long id) throws DaoException;

    void remove(Long id) throws DaoException;

    void update(T obj) throws DaoException;

    List<T> listAll() throws DaoException;

    Class getEntityClass();

    String getSelectQuery();

    String getInsertQuery();

    String getUpdateQuery();

    String getAllFromTableQuery();
}
