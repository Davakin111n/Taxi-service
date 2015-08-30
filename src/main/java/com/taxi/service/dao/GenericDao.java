package com.taxi.service.dao;

import com.taxi.service.entity.Identifier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public interface GenericDao<T extends Identifier> {

    Connection getConnection();

    T get(Long id);

    T addNew(Identifier obj);

    boolean isExists(Long id);

    void update(T obj);

    List<T> listAll();

    Class getEntityClass();

    String getSelectQuery();

    String getInsertQuery();

    String getUpdateQuery();

    String getAllFromTableQuery();

    void getStatementForUpdateEntity(PreparedStatement preparedStatement, Identifier obj);

    void getStatementForInsertEntity(PreparedStatement preparedStatement, Identifier obj);
}
