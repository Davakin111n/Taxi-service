package com.jean.taxi.daoImpl;

import com.jean.taxi.dao.ClientDao;
import com.jean.taxi.dict.ClientType;
import com.jean.taxi.dict.DateOption;
import com.jean.taxi.entity.ClientGrant;
import com.jean.taxi.entity.User;
import com.jean.taxi.filter.ClientFilter;
import com.jean.taxi.utils.ConnectionHolder;
import com.jean.taxi.utils.DataBaseUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends GenericDaoImpl<User> implements ClientDao {

    private final String CLIENT_TABLE = "jean_taxi_service.client cl JOIN jean_taxi_service.client_grant gr ON cl.id = gr.id_client AND gr.admin = false ";
    private final String CLIENTS_ID = "jean_taxi_service.client cl JOIN jean_taxi_service.client_grant gr WHERE cl.id = gr.id_client AND cl.id = ?";
    private final String INSERT_NEW_USER = "INSERT INTO jean_taxi_service.client(email, address, password, phone, second_phone, third_phone, client_name, client_last_name, skype) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_CLIENT = "jean_taxi_service.client SET email = ?, address = ?, password = ?, phone = ?, second_phone = ?, third_phone = ?, client_name = ?, client_last_name = ?, skype = ? WHERE id = ?";
    private final String SELECT_ALL_MODERATORS = "SELECT * FROM jean_taxi_service.client cl JOIN jean_taxi_service.client_grant gr ON cl.id = gr.id_client AND moderator=true AND gr.admin = false";
    private final String SELECT_ALL_SIMPLE_USERS = "SELECT * FROM jean_taxi_service.client cl JOIN jean_taxi_service.client_grant gr ON cl.id = gr.id_client AND gr.moderator = false AND gr.admin = false";
    private final String SELECT_ALL_ACTIVE_USERS = "SELECT * FROM jean_taxi_service.client cl JOIN jean_taxi_service.client_grant gr ON cl.id = gr.id_client AND gr.admin = false AND gr.active = true";
    private final String SELECT_BAN_LIST_USERS = "SELECT * FROM jean_taxi_service.client cl JOIN jean_taxi_service.client_grant gr ON cl.id = gr.id_client AND gr.active = false AND gr.admin = false";
    private final String SELECT_CLIENT_BY_EMAIL = "SELECT * FROM jean_taxi_service.client cl JOIN jean_taxi_service.client_grant gr ON email =?";

    private final String CLIENT_ALIAS = "cl";
    private final String CLIENT_GRANT_ALIAS = "gr";

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
        convertUpdateEntity(user, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(User user, PreparedStatement preparedStatement) {
        convertNewEntity(user, preparedStatement);
    }

    @Override
    public User parseSingleResultSet(ResultSet resultSet) {
        return convertToEntity(resultSet);
    }

    @Override
    public List<User> parseListResultSet(ResultSet resultSet) {
        List userList = null;
        try {
            if (!resultSet.next()) {
                throw new Exception();
            }
            resultSet.previous();
            userList = convertListToEntity(resultSet);
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long addNew(User user) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            convertNewEntity(user, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                return user.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> listAllModerators() {
        List moderatorList;
        try (PreparedStatement preparedStatement = DataBaseUtil.connectionPool.getConnection().prepareStatement(SELECT_ALL_MODERATORS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            moderatorList = convertListToEntity(resultSet);
            return moderatorList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> listSimpleUsers() {
        List simpleUserList;
        try (PreparedStatement preparedStatement = DataBaseUtil.connectionPool.getConnection().prepareStatement(SELECT_ALL_SIMPLE_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            simpleUserList = convertListToEntity(resultSet);
            return simpleUserList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> clientListByFilter(ClientFilter clientFilter) {
        DateTime dateTime = new DateTime();
        LocalDate localDate = new LocalDate();
        List clientList = null;
        if (clientFilter.getClientType() != null) {
            if (StringUtils.equals(clientFilter.getClientType(), ClientType.ALL.getTitle())) {
                genericStringBuilder.append(SELECT_FROM)
                        .append(CLIENT_TABLE);
            } else if (StringUtils.equals(clientFilter.getClientType(), ClientType.MODERATORS.getTitle())) {
                genericStringBuilder.append(SELECT_ALL_MODERATORS);
            } else if (StringUtils.equals(clientFilter.getClientType(), ClientType.ACTIVE.getTitle())) {
                genericStringBuilder.append(SELECT_ALL_ACTIVE_USERS);
            } else if (StringUtils.equals(clientFilter.getClientType(), ClientType.BANNED.getTitle())) {
                genericStringBuilder.append(SELECT_BAN_LIST_USERS);
            }
        }

        if (clientFilter.getDateValue() != null) {
            if (StringUtils.equals(clientFilter.getDateValue(), DateOption.NO_LIMITS.getTitle())) {
                genericStringBuilder.append(";");
            } else if (StringUtils.equals(clientFilter.getDateValue(), DateOption.BY_TODAY.getTitle())) {
                genericStringBuilder
                        .append("AND cl.create_date LIKE '")
                        .append(dateTime.getYear())
                        .append("-")
                        .append(dateTime.getMonthOfYear())
                        .append("-")
                        .append(dateTime.getDayOfMonth())
                        .append("%';");
            } else if (StringUtils.equals(clientFilter.getDateValue(), DateOption.BY_WEEK.getTitle())) {
                genericStringBuilder
                        .append(" AND cl.create_date BETWEEN CAST('")
                        .append(localDate.withDayOfWeek(DateTimeConstants.MONDAY))
                        .append("' AS DATE) AND current_timestamp();");
            } else if (StringUtils.equals(clientFilter.getDateValue(), DateOption.BY_MONTH.getTitle())) {
                genericStringBuilder
                        .append(" AND cl.create_date BETWEEN CAST('")
                        .append(dateTime.getYear())
                        .append("-")
                        .append(dateTime.getMonthOfYear())
                        .append("-01' AS DATE) AND current_timestamp();");
            }
        }
        try (PreparedStatement preparedStatement = DataBaseUtil.connectionPool.getConnection().prepareStatement(genericStringBuilder.toString())) {
            ResultSet resultSet = preparedStatement.executeQuery();
            clientList = convertListToEntity(resultSet);
            return clientList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dateTime = null;
            localDate = null;
            genericStringBuilder.setLength(0);
        }
        return clientList;
    }

    @Override
    public List<User> banList() {
        List banList;
        try (PreparedStatement preparedStatement = DataBaseUtil.connectionPool.getConnection().prepareStatement(SELECT_BAN_LIST_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            banList = convertListToEntity(resultSet);
            return banList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        User user;
        try (PreparedStatement preparedStatement = DataBaseUtil.connectionPool.getConnection().prepareStatement(SELECT_CLIENT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = convertToEntity(resultSet);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void convertNewEntity(User user, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getPassword());
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

    @Override
    public void convertUpdateEntity(User user, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getPassword());
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

    @Override
    public User convertToEntity(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("cl.id"));
                user.setEmail(resultSet.getString("cl.email"));
                user.setPassword(resultSet.getString("cl.password"));
                user.setAddress(resultSet.getString("cl.address"));
                user.setClientName(resultSet.getString("cl.client_name"));
                user.setClientLastName(resultSet.getString("cl.client_last_name"));
                user.setPhone(resultSet.getString("cl.phone"));
                user.setSkype(resultSet.getString("cl.skype"));
                user.setRegistrationDate(resultSet.getDate("cl.create_date"));
                ClientGrant clientGrant = new ClientGrant();
                clientGrant.setId(resultSet.getLong("gr.id"));
                clientGrant.setClientId(resultSet.getLong("gr.id_client"));
                clientGrant.setAdmin(resultSet.getBoolean("gr.admin"));
                clientGrant.setModerator(resultSet.getBoolean("gr.moderator"));
                clientGrant.setActive(resultSet.getBoolean("gr.active"));
                user.setClientGrant(clientGrant);
                return user;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> convertListToEntity(ResultSet resultSet) {
        ArrayList userList = new ArrayList();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("cl.id"));
                user.setEmail(resultSet.getString("cl.email"));
                user.setPassword(resultSet.getString("cl.password"));
                user.setAddress(resultSet.getString("cl.address"));
                user.setClientName(resultSet.getString("cl.client_name"));
                user.setClientLastName(resultSet.getString("cl.client_last_name"));
                user.setPhone(resultSet.getString("cl.phone"));
                user.setSkype(resultSet.getString("cl.skype"));
                user.setRegistrationDate(resultSet.getDate("cl.create_date"));
                ClientGrant clientGrant = new ClientGrant();
                clientGrant.setId(resultSet.getLong("gr.id"));
                clientGrant.setClientId(resultSet.getLong("gr.id_client"));
                clientGrant.setAdmin(resultSet.getBoolean("gr.admin"));
                clientGrant.setModerator(resultSet.getBoolean("gr.moderator"));
                clientGrant.setActive(resultSet.getBoolean("gr.active"));
                user.setClientGrant(clientGrant);
                userList.add(user);
            }
            return userList;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}