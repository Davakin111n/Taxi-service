package com.taxi.service.daoImpl;

import com.taxi.service.dao.OrderDao;
import com.taxi.service.entity.Order;
import com.taxi.service.entity.OrderAddress;
import com.taxi.service.utils.ConnectionHolder;
import com.taxi.service.utils.DataBaseUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public static final String ORDER_TABLE = "jean_taxi_service.order";
    public static final String ORDERS_ID = "jean_taxi_service.order NATURAL JOIN order_address WHERE id=?";
    public static final String DELETE_QUERY = "jean_taxi_service.order WHERE id=?;";
    public static final String INSERT_NEW_ORDER = "INSERT INTO jean_taxi_service.order(title, note, price, active, begin_address, house_number, porch_number, on_performance, accomplished) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_ORDER = "jean_taxi_service.order SET title = ?, note = ?, price = ?, create_date = ?, active = ?, begin_address =?, house_number = ?, porch_number=?, on_perfomance = ?, accomplished = ? WHERE id =?;";
    public static final String SELECT_FROM_ORDERS_BY_CLIENTS_ID = "SELECT * FROM jean_taxi_service.order ord JOIN order_address ord_ad ON id_client =?;";
    public static final String SELECT_ALL_NOT_ACTIVE_ORDERS = "SELECT * FROM jean_taxi_service.order ord JOIN order_address ord_ad ON active = false;";

    private static final byte GENERIC_FIRST_COLUMN = 1;

    @Override
    public String getSelectQuery() {
        return ORDERS_ID;
    }

    @Override
    public String getInsertQuery() {
        return INSERT_NEW_ORDER;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_ORDER;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    public String getAllFromTableQuery() {
        return ORDER_TABLE;
    }

    @Override
    public void getStatementForUpdateEntity(Order order, PreparedStatement preparedStatement) {
        convertUpdateEntity(order, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(Order order, PreparedStatement preparedStatement) {
        convertNewEntity(order, preparedStatement);
    }

    @Override
    public Order parseSingleResultSet(ResultSet resultSet) {
        try {
            if (!resultSet.next()) {
                throw new Exception();
            }
            Order order = convertToEntity(resultSet);
            return order;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> parseListResultSet(ResultSet resultSet) {
        List orderList = null;
        try {
            if (!resultSet.next()) {
                throw new Exception();
            }
            Order order = convertToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long addNew(Order order) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(INSERT_NEW_ORDER)) {
            convertNewEntity(order, preparedStatement);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                return order.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(DELETE_FROM
                .concat(ORDERS_ID))) {
            preparedStatement.setLong(GENERIC_FIRST_COLUMN, orderId);
            preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> orderListByClient(Long clientId) {
        List orderList;
        try (PreparedStatement preparedStatement = DataBaseUtil.connectionPool.getConnection().prepareStatement(SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> notActiveOrderListByClient(Long clientId) {
        List orderList;
        try (PreparedStatement preparedStatement = DataBaseUtil.connectionPool.getConnection().prepareStatement(SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> returnsOrderListByClient(Long clientId) {
        List orderList;
        try (PreparedStatement preparedStatement = DataBaseUtil.connectionPool.getConnection().prepareStatement(SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> notActiveOrderList() {
        List orderList;
        try (PreparedStatement preparedStatement = DataBaseUtil.connectionPool.getConnection().prepareStatement(SELECT_ALL_NOT_ACTIVE_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void convertNewEntity(Order order, PreparedStatement preparedStatement) {
        try {
            int statementValueCount = 1;
            preparedStatement.setString(statementValueCount++, order.getTitle());
            preparedStatement.setString(statementValueCount++, order.getNote());
            preparedStatement.setString(statementValueCount++, order.getPrice());
            preparedStatement.setDate(statementValueCount++, (Date) order.getCreateDate());
            preparedStatement.setBoolean(statementValueCount++, order.isActive());
            preparedStatement.setString(statementValueCount++, order.getBeginAddress());
            preparedStatement.setString(statementValueCount++, order.getHouseNumber());
            preparedStatement.setString(statementValueCount++, order.getPorchNumber());
            preparedStatement.setBoolean(statementValueCount++, order.isOnPerfomance());
            preparedStatement.setBoolean(statementValueCount++, order.isAccomplished());
            preparedStatement.setLong(statementValueCount++, order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void convertUpdateEntity(Order order, PreparedStatement preparedStatement) {
        try {
            int statementValueCount = 1;
            preparedStatement.setString(statementValueCount++, order.getTitle());
            preparedStatement.setString(statementValueCount++, order.getNote());
            preparedStatement.setString(statementValueCount++, order.getPrice());
            preparedStatement.setDate(statementValueCount++, (Date) order.getCreateDate());
            preparedStatement.setBoolean(statementValueCount++, order.isActive());
            preparedStatement.setString(statementValueCount++, order.getBeginAddress());
            preparedStatement.setString(statementValueCount++, order.getHouseNumber());
            preparedStatement.setString(statementValueCount++, order.getPorchNumber());
            preparedStatement.setBoolean(statementValueCount++, order.isOnPerfomance());
            preparedStatement.setBoolean(statementValueCount++, order.isAccomplished());
            preparedStatement.setLong(statementValueCount++, order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order convertToEntity(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setTitle(resultSet.getString("title"));
                order.setNote(resultSet.getString("note"));
                order.setPrice(resultSet.getString("price"));
                order.setCreateDate(resultSet.getDate("create_date"));
                order.setActive(resultSet.getBoolean("active"));
                order.setBeginAddress(resultSet.getString("begin_address"));
                order.setHouseNumber(resultSet.getString("porch_number"));
                order.setOnPerfomance(resultSet.getBoolean("on_performance"));
                order.setOnPerfomance(resultSet.getBoolean("on_performance"));
                order.setAccomplished(resultSet.getBoolean("accomplished"));
                ArrayList orderAddressList = new ArrayList();
                do {
                    OrderAddress orderAddress = new OrderAddress();
                    orderAddress.setId(resultSet.getLong("id"));
                    orderAddress.setId(resultSet.getLong("id_order"));
                    orderAddress.setDestinationAddress(resultSet.getString("destination_address"));
                    orderAddress.setDestinationDate(resultSet.getDate("destination_date"));
                    orderAddress.setDestinationHouseNumber(resultSet.getString("destination_house_number"));
                    orderAddress.setDestinationPorchNumber(resultSet.getString("destination_porch_number"));
                    orderAddressList.add(orderAddress);
                } while (resultSet.next());
                order.setAddressList(orderAddressList);
                return order;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> convertListToEntity(ResultSet resultSet) {
        ArrayList orderList = new ArrayList();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setTitle(resultSet.getString("title"));
                order.setNote(resultSet.getString("note"));
                order.setPrice(resultSet.getString("price"));
                order.setCreateDate(resultSet.getDate("create_date"));
                order.setActive(resultSet.getBoolean("active"));
                order.setBeginAddress(resultSet.getString("begin_address"));
                order.setHouseNumber(resultSet.getString("porch_number"));
                order.setOnPerfomance(resultSet.getBoolean("on_performance"));
                order.setOnPerfomance(resultSet.getBoolean("on_performance"));
                order.setAccomplished(resultSet.getBoolean("accomplished"));
                ArrayList orderAddressList = new ArrayList();
                do {
                    OrderAddress orderAddress = new OrderAddress();
                    orderAddress.setId(resultSet.getLong("id"));
                    orderAddress.setId(resultSet.getLong("id_order"));
                    orderAddress.setDestinationAddress(resultSet.getString("destination_address"));
                    orderAddress.setDestinationDate(resultSet.getDate("destination_date"));
                    orderAddress.setDestinationHouseNumber(resultSet.getString("destination_house_number"));
                    orderAddress.setDestinationPorchNumber(resultSet.getString("destination_porch_number"));
                    orderAddressList.add(orderAddress);
                } while (resultSet.next());
                orderList.add(order);
            }
            return orderList;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
