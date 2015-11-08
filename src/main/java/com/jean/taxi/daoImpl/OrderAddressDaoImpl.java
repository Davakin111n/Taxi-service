package com.jean.taxi.daoImpl;

import com.jean.taxi.entity.OrderAddress;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.utils.ConnectionHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderAddressDaoImpl extends GenericDaoImpl<OrderAddress> {
    private final String ORDER_ADDRESS_TABLE = "`ORDER_ADDRESS` ORD_AD";
    private final String ORDER_ADDRESS_ID = "`ORDER_ADDRESS` WHERE ORD_AD.ID=?;";
    private final String DELETE_ADDRESS = "`ORDER_ADDRESS` WHERE ORD_AD.ID_ORDER=?;";
    private final String INSERT_ORDER_ADDRESS = "INSERT INTO ORDER_ADDRESS(ID_ORDER, DESTINATION_ADDRESS, DESTINATION_HOUSE_NUMBER, DESTINATION_PORCH_NUMBER) VALUES(?,?,?,?);";
    private final String UPDATE_ORDER_ADDRESS = "`ORDER_ADDRESS` ORD_AD SET DESTINATION_ADDRESS=?, DESTINATION_HOUSE_NUMBER=?, DESTINATION_PORCH_NUMBER=? WHERE ORD_AD.ID=?;";

    public OrderAddressDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
    public void getStatementForUpdateEntity(OrderAddress orderAddress, PreparedStatement preparedStatement) throws DaoException {
        convertUpdateEntity(orderAddress, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(OrderAddress orderAddress, PreparedStatement preparedStatement) throws DaoException {
        convertNewEntity(orderAddress, preparedStatement);
    }

    @Override
    public OrderAddress parseSingleResultSet(ResultSet resultSet) throws DaoException {
        return convertToEntity(resultSet);
    }

    @Override
    public List<OrderAddress> parseListResultSet(ResultSet resultSet) throws DaoException {
        return convertListToEntity(resultSet);
    }

    @Override
    public Long addNew(OrderAddress orderAddress) throws DaoException {
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(INSERT_ORDER_ADDRESS, Statement.RETURN_GENERATED_KEYS)) {
            convertNewEntity(orderAddress, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                orderAddress.setId(resultSet.getLong(1));
                return orderAddress.getId();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't add new order address.", e);
        }
        return null;
    }

    @Override
    public void convertNewEntity(OrderAddress orderAddress, PreparedStatement preparedStatement) throws DaoException {
        try {
            preparedStatement.setLong(1, orderAddress.getOrderId());
            preparedStatement.setString(2, orderAddress.getDestinationAddress());
            preparedStatement.setString(3, orderAddress.getDestinationHouseNumber());
            preparedStatement.setString(4, orderAddress.getDestinationPorchNumber());
        } catch (SQLException e) {
            throw new DaoException("Can't convert new order address entity in converter.", e);
        }
    }

    @Override
    public void convertUpdateEntity(OrderAddress orderAddress, PreparedStatement preparedStatement) throws DaoException {
        try {
            preparedStatement.setString(1, orderAddress.getDestinationAddress());
            preparedStatement.setString(2, orderAddress.getDestinationHouseNumber());
            preparedStatement.setString(3, orderAddress.getDestinationPorchNumber());
            preparedStatement.setLong(4, orderAddress.getId());
        } catch (SQLException e) {
            throw new DaoException("Can't update order address entity in converter.", e);
        }
    }

    @Override
    public OrderAddress convertToEntity(ResultSet resultSet) throws DaoException {
        OrderAddress orderAddress = null;
        try {
            while (resultSet.next()) {
                orderAddress = new OrderAddress();
                orderAddress.setId(resultSet.getLong("ORD_AD.ID"));
                orderAddress.setOrderId(resultSet.getLong("ORD_AD.ID_ORDER"));
                orderAddress.setDestinationAddress(resultSet.getString("ORD_AD.DESTINATION_ADDRESS"));
                orderAddress.setDestinationHouseNumber(resultSet.getString("ORD_AD.DESTINATION_HOUSE_NUMBER"));
                orderAddress.setDestinationPorchNumber(resultSet.getString("ORD_AD.DESTINATION_PORCH_NUMBER"));
            }
        } catch (SQLException e) {
            throw new DaoException("Can't convert order address to entity in converter.", e);
        }
        return orderAddress;
    }

    @Override
    public List<OrderAddress> convertListToEntity(ResultSet resultSet) throws DaoException {
        List<OrderAddress> reviewList = new ArrayList<OrderAddress>();
        try {
            while (resultSet.next()) {
                OrderAddress orderAddress = new OrderAddress();
                orderAddress.setId(resultSet.getLong("ORD_AD.ID"));
                orderAddress.setOrderId(resultSet.getLong("ORD_AD.ID_ORDER"));
                orderAddress.setDestinationAddress(resultSet.getString("ORD_AD.DESTINATION_ADDRESS"));
                orderAddress.setDestinationHouseNumber(resultSet.getString("ORD_AD.DESTINATION_HOUSE_NUMBER"));
                orderAddress.setDestinationPorchNumber(resultSet.getString("ORD_AD.DESTINATION_PORCH_NUMBER"));
                reviewList.add(orderAddress);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't convert order address list to entities.", e);
        }
        return reviewList;
    }
}
