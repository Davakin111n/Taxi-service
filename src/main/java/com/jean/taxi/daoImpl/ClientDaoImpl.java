package com.jean.taxi.daoImpl;

import com.jean.taxi.dao.ClientDao;
import com.jean.taxi.dict.ClientType;
import com.jean.taxi.dict.DateOption;
import com.jean.taxi.entity.ClientGrant;
import com.jean.taxi.entity.User;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.filter.ClientFilter;
import com.jean.taxi.utils.ConnectionHolder;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends GenericDaoImpl<User> implements ClientDao {

    private final String CLIENT_TABLE = "`CLIENT` CL JOIN `CLIENT_GRANT` GR ON CL.ID = GR.ID_CLIENT AND GR.ADMIN = false ";
    private final String CLIENTS_ID = "`CLIENT` CL JOIN `CLIENT_GRANT` GR WHERE CL.ID = GR.ID_CLIENT AND CL.ID = ?";
    private final String INSERT_NEW_USER = "INSERT INTO `CLIENT`(EMAIL, ADDRESS, PASSWORD, PHONE, SECOND_PHONE, THIRD_PHONE, CLIENT_NAME, CLIENT_LAST_NAME, SKYPE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_CLIENT = "`CLIENT` SET EMAIL = ?, ADDRESS = ?, PASSWORD = ?, PHONE = ?, SECOND_PHONE = ?, THIRD_PHONE = ?, CLIENT_NAME = ?, CLIENT_LAST_NAME = ?, SKYPE = ? WHERE ID = ?";
    private final String SELECT_ALL_MODERATORS = "SELECT * FROM `CLIENT` CL JOIN `CLIENT_GRANT` GR ON CL.ID = GR.ID_CLIENT AND MODERATOR=true AND GR.ADMIN = false";
    private final String SELECT_ALL_SIMPLE_USERS = "SELECT * FROM `CLIENT` CL JOIN `CLIENT_GRANT` gr ON CL.ID = GR.ID_CLIENT AND GR.MODERATOR = false AND GR.ADMIN = false";
    private final String SELECT_ALL_ACTIVE_USERS = "SELECT * FROM `CLIENT` CL JOIN `CLIENT_GRANT` GR ON CL.ID = GR.ID_CLIENT AND GR.ADMIN = false AND GR.ACTIVE = true";
    private final String SELECT_BAN_LIST_USERS = "SELECT * FROM `CLIENT` CL JOIN `CLIENT_GRANT` GR ON CL.ID = GR.ID_CLIENT AND GR.ACTIVE = false AND GR.ADMIN = false";
    private final String SELECT_CLIENT_BY_EMAIL = "SELECT * FROM `CLIENT` CL JOIN `CLIENT_GRANT` GR ON EMAIL =?";
    private static final byte GENERIC_FIRST_COLUMN = 1;
    private final String CLIENT_ALIAS = "CL";
    private final String CLIENT_GRANT_ALIAS = "GR";

    public ClientDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
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
    public void getStatementForUpdateEntity(User user, PreparedStatement preparedStatement) throws DaoException {
        convertUpdateEntity(user, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(User user, PreparedStatement preparedStatement) throws DaoException {
        convertNewEntity(user, preparedStatement);
    }

    @Override
    public User parseSingleResultSet(ResultSet resultSet) throws DaoException {
        return convertToEntity(resultSet);
    }

    @Override
    public List<User> parseListResultSet(ResultSet resultSet) throws DaoException {
        List userList = null;
        try {
            if (!resultSet.next()) {
                throw new DaoException("Could not parse result set. Result set is empty!");
            }
            resultSet.previous();
            userList = convertListToEntity(resultSet);
            return userList;
        } catch (SQLException e) {
            throw new DaoException("Could not parse result set!", e);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long addNew(User user) throws DaoException {
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            convertNewEntity(user, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                return user.getId();
            }
        } catch (SQLException e) {
            throw new DaoException("Could not add new user " + user, e);
        }
        return null;
    }

    @Override
    public List<User> listAllModerators() throws DaoException {
        List moderatorList = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_ALL_MODERATORS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            moderatorList = convertListToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Could not view moderator list! ", e);
        }
        return moderatorList;
    }

    @Override
    public List listSimpleUsers() throws DaoException {
        List simpleUserList = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_ALL_SIMPLE_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            simpleUserList = convertListToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Could not view simple grant user's list! ", e);
        }
        return simpleUserList;
    }

    @Override
    public List<User> clientListByFilter(ClientFilter clientFilter) throws DaoException {
        DateTime dateTime = new DateTime();
        LocalDate localDate = new LocalDate();
        List<User> clientList = null;
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
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(genericStringBuilder.toString())) {
            ResultSet resultSet = preparedStatement.executeQuery();
            clientList = convertListToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Could not view client list by filter! ", e);
        } finally {
            dateTime = null;
            localDate = null;
            genericStringBuilder.setLength(0);
        }
        return clientList;
    }

    @Override
    public List<User> banList() throws DaoException {
        List<User> banList = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_BAN_LIST_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            banList = convertListToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Couldn't load band list.", e);
        }
        return banList;
    }

    @Override
    public User getByEmail(String email) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_CLIENT_BY_EMAIL)) {
            preparedStatement.setString(GENERIC_FIRST_COLUMN, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = convertToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Can't get client by email.", e);
        }
        return user;
    }

    @Override
    public void convertNewEntity(User user, PreparedStatement preparedStatement) throws DaoException {
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
        } catch (SQLException e) {
            throw new DaoException("Prepared statement error in new Client entity converter", e);
        }
    }

    @Override
    public void convertUpdateEntity(User user, PreparedStatement preparedStatement) throws DaoException {
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
        } catch (SQLException e) {
            throw new DaoException("Prepared statement error in update Client entity converter", e);
        }
    }

    @Override
    public User convertToEntity(ResultSet resultSet) throws DaoException {
        User user = null;
        try {
            while (resultSet.next()) {
                user = new User();
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
            }
        } catch (SQLException e) {
            throw new DaoException("Result set parse error in converter Client to entity.", e);
        }
        return user;
    }

    @Override
    public List<User> convertListToEntity(ResultSet resultSet) throws DaoException {
        List<User> userList = new ArrayList<User>();
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
                ClientGrant clientGrant = user.getClientGrant();
                clientGrant.setId(resultSet.getLong("gr.id"));
                clientGrant.setClientId(resultSet.getLong("gr.id_client"));
                clientGrant.setAdmin(resultSet.getBoolean("gr.admin"));
                clientGrant.setModerator(resultSet.getBoolean("gr.moderator"));
                clientGrant.setActive(resultSet.getBoolean("gr.active"));
                user.setClientGrant(clientGrant);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't parse user list in converter list to entity", e);
        }
        return userList;
    }
}
