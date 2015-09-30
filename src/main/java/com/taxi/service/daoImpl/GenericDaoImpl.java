package com.taxi.service.daoImpl;

import com.taxi.service.dao.GenericDao;
import com.taxi.service.entity.Identifier;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDaoImpl<T extends Identifier> implements GenericDao {

    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String UPDATE = "UPDATE ";
    public static final String DELETE_FROM = "DELETE FROM ";

    private static final byte genericSetValue = 1;

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

    public abstract T parseSingleResultSet(ResultSet resultSet);

    public abstract List<T> parseListResultSet(ResultSet resultSet);

    public abstract T addNew(T identifier);

    public T get(Long id) {
        T identifier;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_FROM
                .concat(getSelectQuery()))) {
            preparedStatement.setLong(genericSetValue, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            identifier = parseSingleResultSet(resultSet);
            return identifier;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(Long id) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(DELETE_FROM
                .concat(getDeleteQuery()))) {
            preparedStatement.setLong(genericSetValue, id);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isExists(Long id) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_FROM
                .concat(getSelectQuery()))) {
            preparedStatement.setLong(genericSetValue, id);
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
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(UPDATE
                .concat(getUpdateQuery()))) {
            getStatementForUpdateEntity((T) obj, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List listAll() {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_FROM
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
