package com.jean.taxi.dao;

import com.jean.taxi.entity.Identifier;

import java.util.List;

public interface GenericDao<T extends Identifier> {

    T get(Long id);

    boolean isExists(Long id);

    void remove(Long id);

    void update(T obj);

    List<T> listAll();

    Class getEntityClass();

    String getSelectQuery();

    String getInsertQuery();

    String getUpdateQuery();

    String getAllFromTableQuery();
}
