package com.taxi.service.daoImpl;

import com.taxi.service.dao.OrderDao;
import com.taxi.service.entity.Order;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    public static final String ORDER_TABLE = "jean_taxi_service.order";
    public static final String ORDERS_ID = "jean_taxi_service.order NATURAL JOIN order_address WHERE id=?";
    public static final String INSERT_NEW_ORDER = "INSERT INTO jean_taxi_service.order(title, note, price, active, begin_address, house_number, porch_number, on_performance, accomplished) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_ORDER = "jean_taxi_service.order SET title = ?, note = ?, price = ?, create_date = ?, active = ?, begin_address =?, house_number = ?, porch_number=?, on_perfomance = ?, accomplished = ? WHERE id =?;";
    public static final String SELECT_FROM_ORDERS_BY_CLIENTS_ID = "jean_taxi_service.order NATURAL JOIN order_address WHERE id_client =?;";
    public static final String SELECT_ALL_NOT_ACTIVE_ORDERS = "SELECT * FROM jean_taxi_service.order NATURAL JOIN order_address WHERE active = false;";

    private static final byte GENERIC_FIRST_COLUMN = 1;

    public OrderDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

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
        return ORDERS_ID;
    }

    @Override
    public String getAllFromTableQuery() {
        return ORDER_TABLE;
    }

    @Override
    public void getStatementForUpdateEntity(Order order, PreparedStatement preparedStatement) {
        convertUpdateOrderEntity(order, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(Order order, PreparedStatement preparedStatement) {
        convertNewOrderEntity(order, preparedStatement);
    }

    @Override
    public Order parseSingleResultSet(ResultSet resultSet) {
        try {
            if (!resultSet.next()) {
                throw new Exception();
            }
            Order order = convertOrderToEntity(resultSet);
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
            Order order = convertOrderToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order addNew(Order order) {
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(INSERT_NEW_ORDER)) {
            convertNewOrderEntity(order, preparedStatement);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            order = parseSingleResultSet(resultSet);
            return order;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(DELETE_FROM
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
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListOrderToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> notActiveOrderListByClient(Long clientId) {
        List orderList;
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListOrderToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> returnsOrderListByClient(Long clientId) {
        List orderList;
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListOrderToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> notActiveOrderList() {
        List orderList;
        try (PreparedStatement preparedStatement = getDataSource().getConnection().prepareStatement(SELECT_ALL_NOT_ACTIVE_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListOrderToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void convertNewOrderEntity(Order order, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, order.getTitle());
            preparedStatement.setString(2, order.getNote());
            preparedStatement.setString(3, order.getPrice());
            preparedStatement.setDate(6, (Date) order.getCreateDate());
            preparedStatement.setLong(7, order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void convertUpdateOrderEntity(Order order, PreparedStatement preparedStatement) {
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

    private Order convertOrderToEntity(ResultSet resultSet) {
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
                return order;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public List<Order> convertListOrderToEntity(ResultSet resultSet) {
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
                orderList.add(order);
            }
            return orderList;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
