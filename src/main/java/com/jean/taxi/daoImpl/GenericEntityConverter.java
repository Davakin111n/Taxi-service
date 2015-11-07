package com.jean.taxi.daoImpl;

import com.jean.taxi.entity.Identifier;
import com.jean.taxi.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class GenericEntityConverter<T extends Identifier> {
    abstract void convertNewEntity(T entity, PreparedStatement preparedStatement) throws DaoException;

    abstract void convertUpdateEntity(T entity, PreparedStatement preparedStatement) throws DaoException;

    abstract T convertToEntity(ResultSet resultSet) throws DaoException;

    abstract List<T> convertListToEntity(ResultSet resultSet) throws DaoException;
}
