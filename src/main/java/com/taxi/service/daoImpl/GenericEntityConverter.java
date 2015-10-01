package com.taxi.service.daoImpl;

import com.taxi.service.entity.Identifier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class GenericEntityConverter<T extends Identifier> {
    abstract void convertNewEntity(T entity, PreparedStatement preparedStatement);

    abstract void convertUpdateEntity(T entity, PreparedStatement preparedStatement);

    abstract T convertToEntity(ResultSet resultSet);

    abstract List<T> convertListToEntity(ResultSet resultSet);
}
