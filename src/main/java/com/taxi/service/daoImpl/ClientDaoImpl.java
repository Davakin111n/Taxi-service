package com.taxi.service.daoImpl;


import com.taxi.service.converter.ConverterFromEntity;
import com.taxi.service.converter.ConverterToEntity;
import com.taxi.service.dao.ClientDao;
import com.taxi.service.dict.SqlQueryList;
import com.taxi.service.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ClientDaoImpl extends GenericDaoImpl<User> implements ClientDao {

    private DataSource dataSource;

    public ClientDaoImpl(Connection connection) {
        super(connection);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getSelectQuery() {
        return SqlQueryList.CLIENTS_ID;
    }

    @Override
    public String getInsertQuery() {
        return SqlQueryList.INSERT_NEW_USER;
    }

    @Override
    public String getUpdateQuery() {
        return SqlQueryList.UPDATE_CLIENT;
    }

    @Override
    public String getAllFromTableQuery() {
        return SqlQueryList.CLIENT_TABLE;
    }

    @Override
    public void getStatementForUpdateEntity(User user, PreparedStatement preparedStatement) {
        ConverterFromEntity.convertUpdateClientEntity(user, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(User user, PreparedStatement preparedStatement) {
        ConverterFromEntity.convertNewClientEntity(user, preparedStatement);
    }

    @Override
    public User parseSingleResultSet(ResultSet resultSet) {
        try {
            if (!resultSet.next()) {
                throw new Exception();
            }
            User user = ConverterToEntity.convertUserToEntity(resultSet);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> parseListResultSet(ResultSet resultSet) {
        List userList = null;
        try {
            if (!resultSet.next()) {
                throw new Exception();
            }
            User user = ConverterToEntity.convertUserToEntity(resultSet);
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User addNew(User user) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SqlQueryList.INSERT_NEW_USER)) {
            ConverterFromEntity.convertNewClientEntity(user, preparedStatement);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            return parseSingleResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> listAllModerators() {
        List moderatorList;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SqlQueryList.SELECT_ALL_MODERATORS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            moderatorList = ConverterToEntity.convertListUserToEntity(resultSet);
            return moderatorList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        User user;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SqlQueryList.SELECT_CLIENT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = ConverterToEntity.convertUserToEntity(resultSet);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findBySearchRequest(String searchQuery) {
        List userList;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SqlQueryList.SELECT_CLIENTS_BY_SEARCH_QUERY)) {
            ConverterFromEntity.convertBySearchQuery(preparedStatement, searchQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            userList = ConverterToEntity.convertListUserToEntity(resultSet);
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
