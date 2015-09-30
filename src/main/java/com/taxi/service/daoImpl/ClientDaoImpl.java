package com.taxi.service.daoImpl;

import com.taxi.service.dao.ClientDao;
import com.taxi.service.entity.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends GenericDaoImpl<User> implements ClientDao {

    private final String CLIENT_TABLE = "jean_taxi_service.client;";
    private final String CLIENTS_ID = "jean_taxi_service.client JOIN jean_taxi_service.client_grant WHERE id=?;";
    private final String INSERT_NEW_USER = "INSERT INTO jean_taxi_service.client(email, address, password, phone, second_phone, third_phone, client_name, client_last_name, skype) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String UPDATE_CLIENT = "jean_taxi_service.client SET email = ?, address = ?, password = ?, phone = ?, second_phone = ?, third_phone = ?, client_name = ?, client_last_name = ?, skype = ? WHERE id = ?;";
    private final String SELECT_ALL_MODERATORS = "SELECT * FROM jean_taxi_service.client JOIN jean_taxi_service.client_grant WHERE moderator=?;";
    private final String SELECT_ALL_SIMPLE_USERS = "SELECT * FROM jean_taxi_service.client JOIN jean_taxi_service.client_grant WHERE moderator = false AND admin = false;";
    private final String SELECT_CLIENT_BY_EMAIL = "SELECT * FROM jean_taxi_service.client JOIN jean_taxi_service.client_grant WHERE email =?;";

    public ClientDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getSelectQuery() {
        return CLIENTS_ID;
    }

    @Override
    public String getInsertQuery() {
        return INSERT_NEW_USER;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_CLIENT;
    }

    @Override
    public String getDeleteQuery() {
        return CLIENTS_ID;
    }

    @Override
    public String getAllFromTableQuery() {
        return CLIENT_TABLE;
    }

    @Override
    public void getStatementForUpdateEntity(User user, PreparedStatement preparedStatement) {
        convertUpdateClientEntity(user, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(User user, PreparedStatement preparedStatement) {
        convertNewClientEntity(user, preparedStatement);
    }

    @Override
    public User parseSingleResultSet(ResultSet resultSet) {
        return convertUserToEntity(resultSet);
    }

    @Override
    public List<User> parseListResultSet(ResultSet resultSet) {
        List userList = null;
        try {
            if (!resultSet.next()) {
                throw new Exception();
            }
            User user = convertUserToEntity(resultSet);
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User addNew(User user) {
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            convertNewClientEntity(user, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> listAllModerators() {
        List moderatorList;
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(SELECT_ALL_MODERATORS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            moderatorList = convertListUserToEntity(resultSet);
            return moderatorList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> listSimpleUsers() {
        List simpleUserList;
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(SELECT_ALL_SIMPLE_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            simpleUserList = convertListUserToEntity(resultSet);
            return simpleUserList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        User user;
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(SELECT_CLIENT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = convertUserToEntity(resultSet);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void convertNewClientEntity(User user, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getSecondPhone());
            preparedStatement.setString(6, user.getThirdPhone());
            preparedStatement.setString(7, user.getClientName());
            preparedStatement.setString(8, user.getClientLastName());
            preparedStatement.setString(9, user.getSkype());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void convertUpdateClientEntity(User user, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getSecondPhone());
            preparedStatement.setString(6, user.getThirdPhone());
            preparedStatement.setString(7, user.getClientName());
            preparedStatement.setString(8, user.getClientLastName());
            preparedStatement.setString(9, user.getSkype());
            preparedStatement.setLong(10, user.getId());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public User convertUserToEntity(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAddress(resultSet.getString("address"));
                user.setClientName(resultSet.getString("client_name"));
                user.setClientLastName(resultSet.getString("client_last_name"));
                user.setPhone(resultSet.getString("phone"));
                user.setSkype(resultSet.getString("skype"));
                user.setRegistrationDate(resultSet.getDate("create_date"));
                return user;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public List<User> convertListUserToEntity(ResultSet resultSet) {
        ArrayList userList = new ArrayList();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAddress(resultSet.getString("address"));
                user.setClientName(resultSet.getString("client_name"));
                user.setClientLastName(resultSet.getString("client_last_name"));
                user.setPhone(resultSet.getString("phone"));
                user.setSkype(resultSet.getString("skype"));
                user.setRegistrationDate(resultSet.getDate("create_date"));
                userList.add(user);
            }
            return userList;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
