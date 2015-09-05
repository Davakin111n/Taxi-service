package com.taxi.service.daoImpl;

import com.taxi.service.converter.ConverterFromEntity;
import com.taxi.service.converter.ConverterToEntity;
import com.taxi.service.dao.OrderDao;
import com.taxi.service.dict.SqlQueryList;
import com.taxi.service.entity.Order;
import com.taxi.service.filter.OrderFilter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return SqlQueryList.ORDERS_ID;
    }

    @Override
    public String getInsertQuery() {
        return SqlQueryList.INSERT_NEW_ORDER;
    }

    @Override
    public String getUpdateQuery() {
        return SqlQueryList.UPDATE_ORDER;
    }

    @Override
    public String getAllFromTableQuery() {
        return SqlQueryList.ORDER_TABLE;
    }

    @Override
    public void getStatementForUpdateEntity(PreparedStatement preparedStatement, Order order) {
        ConverterFromEntity.convertUpdateOrderEntity(order, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(PreparedStatement preparedStatement, Order order) {
        ConverterFromEntity.convertNewOrderEntity(order, preparedStatement);
    }

    @Override
    public Order parseSingleResultSet(ResultSet resultSet) {
        try {
            if (!resultSet.next()) {
                throw new Exception();
            }
            Order order = ConverterToEntity.convertOrderToEntity(resultSet);
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
            Order order = ConverterToEntity.convertOrderToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order addNew(Order order) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SqlQueryList.INSERT_NEW_ORDER)) {
            getStatementForInsertEntity(preparedStatement, order);
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
    public List<Order> listByFilter(OrderFilter orderFilter) {
        return null;
    }

    @Override
    public List<Order> orderListByClient(Long clientId) {
        List orderList;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SqlQueryList.SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = ConverterToEntity.convertListOrderToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> notActiveOrderListByClient(Long clientId) {
        List orderList;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SqlQueryList.SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = ConverterToEntity.convertListOrderToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> returnsOrderListByClient(Long clientId) {
        List orderList;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SqlQueryList.SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = ConverterToEntity.convertListOrderToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> notActiveOrderList() {
        List orderList;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SqlQueryList.SELECT_ALL_NOT_ACTIVE_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = ConverterToEntity.convertListOrderToEntity(resultSet);
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
