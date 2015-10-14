package com.taxi.service.daoImpl;

import com.taxi.service.dao.OrderDao;
import com.taxi.service.entity.Order;
import com.taxi.service.entity.OrderAddress;
import com.taxi.service.utils.ConnectionHolder;
import com.taxi.service.utils.DataBaseUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public static final String ORDER_TABLE = "jean_taxi_service.order";
    public static final String ORDERS_ID = "jean_taxi_service.order NATURAL JOIN order_address WHERE id=?";
    public static final String DELETE_QUERY = "jean_taxi_service.order WHERE id=?;";
    public static final String INSERT_NEW_ORDER = "INSERT INTO jean_taxi_service.order(id_client, title, note, price, active, begin_address, house_number, porch_number, on_performance, accomplished, phone, contact_name, car_option) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_ORDER = "jean_taxi_service.order SET title = ?, note = ?, price = ?, create_date = ?, active = ?, begin_address =?, house_number = ?, porch_number=?, on_perfomance = ?, accomplished = ? , phone=?, contact_name=?, car_option=? WHERE id =?;";
    public static final String SELECT_FROM_ORDERS_BY_CLIENTS_ID = "SELECT * FROM jean_taxi_service.order ord JOIN order_address ord_ad ON ord.id_client=? AND ord_ad.id_order = ord.id;";
    public static final String SELECT_ALL_NOT_ACTIVE_ORDERS = "SELECT * FROM jean_taxi_service.order ord JOIN order_address ord_ad ON ord.active = false AND ord.id = ord_ad.id_order";

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
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(INSERT_NEW_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            convertNewEntity(order, preparedStatement);
            preparedStatement.executeUpdate();
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
            preparedStatement.setLong(1, clientId);
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
            preparedStatement.setLong(1, clientId);
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
            preparedStatement.setLong(statementValueCount++, order.getClientId());
            preparedStatement.setString(statementValueCount++, order.getTitle());
            preparedStatement.setString(statementValueCount++, order.getNote());
            preparedStatement.setString(statementValueCount++, order.getPrice());
            preparedStatement.setBoolean(statementValueCount++, order.isActive());
            preparedStatement.setString(statementValueCount++, order.getBeginAddress());
            preparedStatement.setString(statementValueCount++, order.getHouseNumber());
            preparedStatement.setString(statementValueCount++, order.getPorchNumber());
            preparedStatement.setBoolean(statementValueCount++, order.isOnPerfomance());
            preparedStatement.setBoolean(statementValueCount++, order.isAccomplished());
            preparedStatement.setString(statementValueCount++, order.getPhone());
            preparedStatement.setString(statementValueCount++, order.getContactName());
            preparedStatement.setString(statementValueCount++, order.getCarOption());
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
            preparedStatement.setBoolean(statementValueCount++, order.isActive());
            preparedStatement.setString(statementValueCount++, order.getBeginAddress());
            preparedStatement.setString(statementValueCount++, order.getHouseNumber());
            preparedStatement.setString(statementValueCount++, order.getPorchNumber());
            preparedStatement.setBoolean(statementValueCount++, order.isOnPerfomance());
            preparedStatement.setBoolean(statementValueCount++, order.isAccomplished());
            preparedStatement.setString(statementValueCount++, order.getPhone());
            preparedStatement.setString(statementValueCount++, order.getContactName());
            preparedStatement.setString(statementValueCount++, order.getCarOption());
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
                order.setId(resultSet.getLong("ord.id"));
                order.setClientId(resultSet.getLong("ord.id_client"));
                order.setTitle(resultSet.getString("ord.title"));
                order.setNote(resultSet.getString("ord.note"));
                order.setPrice(resultSet.getString("ord.price"));
                order.setCreateDate(resultSet.getDate("ord.create_date"));
                order.setActive(resultSet.getBoolean("ord.active"));
                order.setBeginAddress(resultSet.getString("ord.begin_address"));
                order.setHouseNumber(resultSet.getString("ord.porch_number"));
                order.setOnPerfomance(resultSet.getBoolean("ord.on_performance"));
                order.setOnPerfomance(resultSet.getBoolean("ord.on_performance"));
                order.setAccomplished(resultSet.getBoolean("ord.accomplished"));
                order.setPhone(resultSet.getString("ord.phone"));
                order.setContactName(resultSet.getString("ord.contact_name"));
                order.setCarOption(resultSet.getString("ord.car_option"));
                ArrayList orderAddressList = new ArrayList();
                do {
                    OrderAddress orderAddress = new OrderAddress();
                    orderAddress.setId(resultSet.getLong("ord_ad.id"));
                    orderAddress.setId(resultSet.getLong("ord_ad.id_order"));
                    orderAddress.setDestinationAddress(resultSet.getString("ord_ad.destination_address"));
                    orderAddress.setDestinationHouseNumber(resultSet.getString("ord_ad.destination_house_number"));
                    orderAddress.setDestinationPorchNumber(resultSet.getString("ord_ad.destination_porch_number"));
                    orderAddressList.add(orderAddress);
                } while (resultSet.next() && resultSet.getLong("ord.id") == order.getId());
                resultSet.previous();
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
                order.setId(resultSet.getLong("ord.id"));
                order.setClientId(resultSet.getLong("ord.id_client"));
                order.setTitle(resultSet.getString("ord.title"));
                order.setNote(resultSet.getString("ord.note"));
                order.setPrice(resultSet.getString("ord.price"));
                order.setCreateDate(resultSet.getDate("ord.create_date"));
                order.setActive(resultSet.getBoolean("ord.active"));
                order.setBeginAddress(resultSet.getString("ord.begin_address"));
                order.setHouseNumber(resultSet.getString("ord.porch_number"));
                order.setOnPerfomance(resultSet.getBoolean("ord.on_performance"));
                order.setOnPerfomance(resultSet.getBoolean("ord.on_performance"));
                order.setAccomplished(resultSet.getBoolean("ord.accomplished"));
                order.setPhone(resultSet.getString("ord.phone"));
                order.setContactName(resultSet.getString("ord.contact_name"));
                order.setCarOption(resultSet.getString("ord.car_option"));
                List orderAddressList = order.getAddressList();
                do {
                    OrderAddress orderAddress = new OrderAddress();
                    orderAddress.setId(resultSet.getLong("ord_ad.id"));
                    orderAddress.setId(resultSet.getLong("ord_ad.id_order"));
                    orderAddress.setDestinationAddress(resultSet.getString("ord_ad.destination_address"));
                    orderAddress.setDestinationHouseNumber(resultSet.getString("ord_ad.destination_house_number"));
                    orderAddress.setDestinationPorchNumber(resultSet.getString("ord_ad.destination_porch_number"));
                    orderAddressList.add(orderAddress);
                } while (resultSet.next() && resultSet.getLong("ord.id") == order.getId());
                resultSet.previous();
                order.setAddressList(orderAddressList);
                orderList.add(order);
            }
            return orderList;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
