package com.taxi.service.daoImpl;


import com.taxi.service.entity.ClientGrant;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientGrantDaoImpl extends ClientDaoImpl {

    private final String INSERT_NEW_CLIENT_GRANT = "INSERT INTO jean_taxi_service.client_grant(`id_client`,`admin`,`moderator`,`active`) VALUES(?,?,?,?);";

    public ClientGrantDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public void createClientGrant(Long clientId, ClientGrant clientGrant) {
        byte valueCounter = 1;
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(INSERT_NEW_CLIENT_GRANT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(valueCounter++, clientId);
            preparedStatement.setBoolean(valueCounter++, clientGrant.isAdmin());
            preparedStatement.setBoolean(valueCounter++, clientGrant.isModerator());
            preparedStatement.setBoolean(valueCounter++, clientGrant.isActive());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
