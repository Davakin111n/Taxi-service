package com.taxi.service.daoImpl;

import com.taxi.service.entity.OrderAddress;
import com.taxi.service.utils.ConnectionHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderAddressDaoImpl extends GenericDaoImpl<OrderAddress> {
    private final String ORDER_ADDRESS_TABLE = "jean_taxi_service.order_address;";
    private final String ORDER_ADDRESS_ID = "jean_taxi_service.order_address WHERE id=?;";
    private final String DELETE_ADDRESS = "jean_taxi_service.order_address WHERE id_order=?;";
    private final String INSERT_ORDER_ADDRESS = "INSERT INTO jean_taxi_service.order_address(id_order, destination_address, destination_house_number, destination_porch_number) VALUES(?,?,?,?);";
    private final String UPDATE_ORDER_ADDRESS = "jean_taxi_service.order_address SET destination_address=?, destination_house_number=?, destination_porch_number=? WHERE id=?;";

    @Override
    public String getSelectQuery() {
        return ORDER_ADDRESS_ID;
    }

    @Override
    public String getInsertQuery() {
        return INSERT_ORDER_ADDRESS;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_ORDER_ADDRESS;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_ADDRESS;
    }

    @Override
    public String getAllFromTableQuery() {
        return ORDER_ADDRESS_TABLE;
    }

    @Override
    public void getStatementForUpdateEntity(OrderAddress orderAddress, PreparedStatement preparedStatement) {
        convertUpdateEntity(orderAddress, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(OrderAddress orderAddress, PreparedStatement preparedStatement) {
        convertNewEntity(orderAddress, preparedStatement);
    }

    @Override
    public OrderAddress parseSingleResultSet(ResultSet resultSet) {
        return convertToEntity(resultSet);
    }

    @Override
    public List<OrderAddress> parseListResultSet(ResultSet resultSet) {
        return convertListToEntity(resultSet);
    }

    @Override
    public Long addNew(OrderAddress orderAddress) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(INSERT_ORDER_ADDRESS, Statement.RETURN_GENERATED_KEYS)) {
            convertNewEntity(orderAddress, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                orderAddress.setId(resultSet.getLong(1));
                return orderAddress.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void convertNewEntity(OrderAddress orderAddress, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setLong(1, orderAddress.getOrderId());
            preparedStatement.setString(2, orderAddress.getDestinationAddress());
            preparedStatement.setString(3, orderAddress.getDestinationHouseNumber());
            preparedStatement.setString(4, orderAddress.getDestinationPorchNumber());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void convertUpdateEntity(OrderAddress orderAddress, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, orderAddress.getDestinationAddress());
            preparedStatement.setString(2, orderAddress.getDestinationHouseNumber());
            preparedStatement.setString(3, orderAddress.getDestinationPorchNumber());
            preparedStatement.setLong(4, orderAddress.getId());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public OrderAddress convertToEntity(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                OrderAddress orderAddress = new OrderAddress();
                orderAddress.setId(resultSet.getLong("id"));
                orderAddress.setOrderId(resultSet.getLong("id_order"));
                orderAddress.setDestinationAddress(resultSet.getString("destination_address"));
                orderAddress.setDestinationHouseNumber(resultSet.getString("destination_house_number"));
                orderAddress.setDestinationPorchNumber(resultSet.getString("destination_porch_number"));
                return orderAddress;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderAddress> convertListToEntity(ResultSet resultSet) {
        ArrayList reviewList = new ArrayList();
        try {
            while (resultSet.next()) {
                OrderAddress orderAddress = new OrderAddress();
                orderAddress.setId(resultSet.getLong("id"));
                orderAddress.setOrderId(resultSet.getLong("id_order"));
                orderAddress.setDestinationAddress(resultSet.getString("destination_address"));
                orderAddress.setDestinationHouseNumber(resultSet.getString("destination_house_number"));
                orderAddress.setDestinationPorchNumber(resultSet.getString("destination_porch_number"));
                reviewList.add(orderAddress);
            }
            return reviewList;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
