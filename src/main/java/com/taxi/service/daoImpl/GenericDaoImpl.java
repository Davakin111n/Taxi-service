package com.taxi.service.daoImpl;

import com.taxi.service.dao.GenericDao;
import com.taxi.service.dict.SqlQueryList;
import com.taxi.service.entity.Identifier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class GenericDaoImpl<T extends Identifier> implements GenericDao {

    private Connection connection;

    private List<T> list;

    public GenericDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public abstract String getSelectQuery();

    public abstract String getInsertQuery();

    public abstract String getUpdateQuery();

    public abstract String getAllFromTableQuery();

    public abstract void getStatementForUpdateEntity(T obj, PreparedStatement preparedStatement);

    public abstract void getStatementForInsertEntity(T obj, PreparedStatement preparedStatement);

    public abstract T parseSingleResultSet(ResultSet resultSet);

    public abstract List<T> parseListResultSet(ResultSet resultSet);

    public Connection getConnection() {
        return connection;
    }

    public Identifier get(Long id) {
        Identifier identifier = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueryList.SELECT_FROM
                .concat(getSelectQuery()))) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            identifier = parseSingleResultSet(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return identifier;
    }

    public boolean isExists(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueryList.SELECT_FROM
                .concat(getSelectQuery()))) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!list.isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void update(Identifier obj) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueryList.INSERT_INTO
                .concat(getInsertQuery()))) {
            getStatementForUpdateEntity((T) obj, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List listAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueryList.SELECT_FROM
                .concat(getAllFromTableQuery()))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            list = parseListResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Class getEntityClass() {
        return null;
    }
}
