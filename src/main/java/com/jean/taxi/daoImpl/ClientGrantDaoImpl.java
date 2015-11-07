package com.jean.taxi.daoImpl;


import com.jean.taxi.entity.ClientGrant;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.utils.ConnectionHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientGrantDaoImpl extends GenericDaoImpl<ClientGrant> {

    private final String CLIENT_GRANT_TABLE = "`CLIENT_GRANT`;";
    private final String CLIENT_GRANT_ID = "`CLIENT_GRANT` WHERE id=?;";
    private final String INSERT_NEW_CLIENT_GRANT = "INSERT INTO client_grant(`ID_CLIENT`,`ADMIN`,`MODERATOR`,`ACTIVE`) VALUES(?,?,?,?);";
    private final String UPDATE_CLIENT_GRANT = "`CLIENT_GRANT` SET ADMIN=?, MODERATOR=?, ACTIVE=? WHERE ID_CLIENT=?;";

    public ClientGrantDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getSelectQuery() {
        return CLIENT_GRANT_ID;
    }

    @Override
    public String getInsertQuery() {
        return INSERT_NEW_CLIENT_GRANT;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_CLIENT_GRANT;
    }

    @Override
    public String getDeleteQuery() {
        return CLIENT_GRANT_ID;
    }

    @Override
    public String getAllFromTableQuery() {
        return CLIENT_GRANT_TABLE;
    }

    @Override
    public void getStatementForUpdateEntity(ClientGrant clientGrant, PreparedStatement preparedStatement) throws DaoException {
        convertUpdateEntity(clientGrant, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(ClientGrant clientGrant, PreparedStatement preparedStatement) throws DaoException {
        convertNewEntity(clientGrant, preparedStatement);
    }

    @Override
    public ClientGrant parseSingleResultSet(ResultSet resultSet) throws DaoException {
        return convertToEntity(resultSet);
    }

    @Override
    public List<ClientGrant> parseListResultSet(ResultSet resultSet) throws DaoException {
        return convertListToEntity(resultSet);
    }

    @Override
    public Long addNew(ClientGrant clientGrant) throws DaoException {
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(INSERT_NEW_CLIENT_GRANT, Statement.RETURN_GENERATED_KEYS)) {
            convertNewEntity(clientGrant, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                clientGrant.setId(resultSet.getLong(1));
                return clientGrant.getId();
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't add new client grant.", e);
        }
        return null;
    }

    @Override
    void convertNewEntity(ClientGrant clientGrant, PreparedStatement preparedStatement) throws DaoException {
        byte valueCounter = 1;
        try {
            preparedStatement.setLong(valueCounter++, clientGrant.getClientId());
            preparedStatement.setBoolean(valueCounter++, clientGrant.isAdmin());
            preparedStatement.setBoolean(valueCounter++, clientGrant.isModerator());
            preparedStatement.setBoolean(valueCounter++, clientGrant.isActive());
        } catch (SQLException e) {
            throw new DaoException("Couldn't convert client grant new entity.", e);
        }
    }

    @Override
    void convertUpdateEntity(ClientGrant clientGrant, PreparedStatement preparedStatement) throws DaoException {
        byte valueCounter = 1;
        try {
            preparedStatement.setBoolean(valueCounter++, clientGrant.isAdmin());
            preparedStatement.setBoolean(valueCounter++, clientGrant.isModerator());
            preparedStatement.setBoolean(valueCounter++, clientGrant.isActive());
            preparedStatement.setLong(valueCounter++, clientGrant.getClientId());
        } catch (SQLException e) {
            throw new DaoException("Couldn't update client grant entity.", e);
        }
    }

    @Override
    ClientGrant convertToEntity(ResultSet resultSet) throws DaoException {
        ClientGrant clientGrant = null;
        try {
            while (resultSet.next()) {
                clientGrant = new ClientGrant();
                clientGrant.setId(resultSet.getLong("id"));
                clientGrant.setClientId(resultSet.getLong("id_client"));
                clientGrant.setAdmin(resultSet.getBoolean("admin"));
                clientGrant.setModerator(resultSet.getBoolean("moderator"));
                clientGrant.setActive(resultSet.getBoolean("active"));
                return clientGrant;
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't convert to client grant entity.", e);
        }
        return clientGrant;
    }

    @Override
    List<ClientGrant> convertListToEntity(ResultSet resultSet) throws DaoException {
        List<ClientGrant> clientGrantList = new ArrayList<ClientGrant>();
        try {
            while (resultSet.next()) {
                ClientGrant clientGrant = new ClientGrant();
                clientGrant.setId(resultSet.getLong("id"));
                clientGrant.setClientId(resultSet.getLong("id_client"));
                clientGrant.setAdmin(resultSet.getBoolean("admin"));
                clientGrant.setModerator(resultSet.getBoolean("moderator"));
                clientGrant.setActive(resultSet.getBoolean("active"));
                clientGrantList.add(clientGrant);
            }
        } catch (SQLException e) {
            throw new DaoException("Couldn't convert list of client's grant's entities.", e);
        }
        return clientGrantList;
    }
}
