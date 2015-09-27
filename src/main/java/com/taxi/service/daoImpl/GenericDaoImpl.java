package com.taxi.service.daoImpl;

import com.taxi.service.dao.GenericDao;
import com.taxi.service.dict.SqlQueryList;
import com.taxi.service.entity.Identifier;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDaoImpl<T extends Identifier> implements GenericDao {

    private DataSource dataSource;
    private List<T> list;

    public GenericDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public abstract String getSelectQuery();

    public abstract String getInsertQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public abstract String getAllFromTableQuery();

    public abstract void getStatementForUpdateEntity(T obj, PreparedStatement preparedStatement);

    public abstract void getStatementForInsertEntity(T obj, PreparedStatement preparedStatement);

    public abstract T parseGeneratedValues(T obj, ResultSet resultSet);

    public abstract T parseSingleResultSet(ResultSet resultSet);

    public abstract List<T> parseListResultSet(ResultSet resultSet);

    public T get(Long id) {
        T identifier;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SqlQueryList.SELECT_FROM
                .concat(getSelectQuery()))) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            identifier = parseSingleResultSet(resultSet);
            return identifier;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(Long id) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SqlQueryList.DELETE_FROM
                .concat(getDeleteQuery()))) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isExists(Long id) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SqlQueryList.SELECT_FROM
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
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SqlQueryList.UPDATE
                .concat(getUpdateQuery()))) {
            getStatementForUpdateEntity((T) obj, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List listAll() {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SqlQueryList.SELECT_FROM
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
