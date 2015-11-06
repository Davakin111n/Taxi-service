package com.jean.taxi.daoImpl;

import com.jean.taxi.dao.GenericDao;
import com.jean.taxi.entity.Identifier;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.utils.ConnectionHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDaoImpl<T extends Identifier> extends GenericEntityConverter<T> implements GenericDao {

    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String UPDATE = "UPDATE ";
    public static final String DELETE_FROM = "DELETE FROM ";

    private static final byte columnNumber = 1;
    private List<T> list;

    public static final StringBuilder genericStringBuilder = new StringBuilder();

    protected DataSource dataSource;
    protected Connection currentLocalConnection = ConnectionHolder.getLocalConnection();

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setCurrentLocalConnection(Connection currentLocalConnection) {
        this.currentLocalConnection = currentLocalConnection;
    }

    public abstract String getSelectQuery();

    public abstract String getInsertQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public abstract String getAllFromTableQuery();

    public abstract void getStatementForUpdateEntity(T obj, PreparedStatement preparedStatement);

    public abstract void getStatementForInsertEntity(T obj, PreparedStatement preparedStatement);

    public abstract T parseSingleResultSet(ResultSet resultSet);

    public abstract List<T> parseListResultSet(ResultSet resultSet) throws DaoException;

    public abstract Long addNew(T obj) throws DaoException;

    public T get(Long id) {
        T identifier;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_FROM
                .concat(getSelectQuery()))) {
            preparedStatement.setLong(columnNumber, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            identifier = parseSingleResultSet(resultSet);
            return identifier;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(Long id) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(DELETE_FROM
                .concat(getDeleteQuery()))) {
            preparedStatement.setLong(columnNumber, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isExists(Long id) {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_FROM
                .concat(getSelectQuery()))) {
            preparedStatement.setLong(columnNumber, id);
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
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(UPDATE
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
