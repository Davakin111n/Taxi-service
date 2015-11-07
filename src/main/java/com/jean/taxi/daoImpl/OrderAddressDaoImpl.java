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
    private final String ORDER_ADDRESS_TABLE = "`order_address` add";
    private final String ORDER_ADDRESS_ID = "`order_address` WHERE id=?;";
    private final String DELETE_ADDRESS = "`order_address` WHERE id_order=?;";
    private final String INSERT_ORDER_ADDRESS = "INSERT INTO order_address(id_order, destination_address, destination_house_number, destination_porch_number) VALUES(?,?,?,?);";
    private final String UPDATE_ORDER_ADDRESS = "`order_address` SET destination_address=?, destination_house_number=?, destination_porch_number=? WHERE id=?;";

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
                orderAddress.setId(resultSet.getLong("id"));
                orderAddress.setOrderId(resultSet.getLong("id_order"));
                orderAddress.setDestinationAddress(resultSet.getString("destination_address"));
                orderAddress.setDestinationHouseNumber(resultSet.getString("destination_house_number"));
                orderAddress.setDestinationPorchNumber(resultSet.getString("destination_porch_number"));
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
                orderAddress.setId(resultSet.getLong("id"));
                orderAddress.setOrderId(resultSet.getLong("id_order"));
                orderAddress.setDestinationAddress(resultSet.getString("destination_address"));
                orderAddress.setDestinationHouseNumber(resultSet.getString("destination_house_number"));
                orderAddress.setDestinationPorchNumber(resultSet.getString("destination_porch_number"));
                reviewList.add(orderAddress);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't convert order address list to entities.", e);
        }
        return reviewList;
    }
}
