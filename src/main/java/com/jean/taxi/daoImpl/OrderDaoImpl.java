package com.jean.taxi.daoImpl;

import com.jean.taxi.dao.OrderDao;
import com.jean.taxi.dict.DateOption;
import com.jean.taxi.dict.OrderType;
import com.jean.taxi.entity.Order;
import com.jean.taxi.entity.OrderAddress;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.filter.OrderFilter;
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

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    private static final String ORDER_TABLE = "`ORDER` ORD JOIN `ORDER_ADDRESS` ORD_AD ON ORD.ID = ORD_AD.ID_ORDER";
    private static final String ORDERS_ID = "`ORDER` ORD JOIN `ORDER_ADDRESS` ORD_AD ON ORD.ID=? AND ORD_AD.ID_ORDER = ORD.ID";
    private static final String DELETE_QUERY = "`ORDER` ORD WHERE ORD.ID=?;";
    private static final String INSERT_NEW_ORDER = "INSERT INTO `ORDER`(ID_CLIENT, TITLE, NOTE, PRICE, ACTIVE, BEGIN_ADDRESS, HOUSE_NUMBER, PORCH_NUMBER, ON_PERFORMANCE, ACCOMPLISHED, PHONE, CONTACT_NAME, CAR_OPTION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER = "`ORDER` ORD SET TITLE = ?, NOTE = ?, PRICE = ?, ACTIVE = ?, BEGIN_ADDRESS =?, HOUSE_NUMBER = ?, PORCH_NUMBER=?, ON_PERFORMANCE = ?, ACCOMPLISHED = ? , PHONE=?, CONTACT_NAME=?, CAR_OPTION=? WHERE ORD.ID =?";
    private static final String SELECT_FROM_ORDERS_BY_CLIENTS_ID = "SELECT * FROM `ORDER` ORD JOIN `ORDER_ADDRESS` ORD_AD ON ORD.ID_CLIENT=? AND ORD_AD.ID_ORDER = ORD.ID";
    private static final String SELECT_ALL_ACTIVE_ORDERS = "SELECT * FROM `ORDER` ORD JOIN `ORDER_ADDRESS` ORD_AD ON ORD.ACTIVE= true AND ORD.ID = ORD_AD.ID_ORDER";
    private static final String SELECT_ALL_NOT_ACTIVE_ORDERS = "SELECT * FROM `ORDER` ORD JOIN `ORDER_ADDRESS` ORD_AD ON ORD.ACTIVE = false AND ORD.ID = ORD_AD.ID_ORDER";
    private static final String SELECT_ALL_ACCOMPLISHED_ORDERS = "SELECT * FROM `ORDER` ORD JOIN `ORDER_ADDRESS` ORD_AD ON ORD.ACCOMPLISHED = true AND ORD.ID = ORD_AD.ID_ORDER";

    private static final byte GENERIC_FIRST_COLUMN = 1;

    public OrderDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
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
        return DELETE_QUERY;
    }

    @Override
    public String getAllFromTableQuery() {
        return ORDER_TABLE;
    }

    @Override
    public void getStatementForUpdateEntity(Order order, PreparedStatement preparedStatement) throws DaoException {
        convertUpdateEntity(order, preparedStatement);
    }

    @Override
    public void getStatementForInsertEntity(Order order, PreparedStatement preparedStatement) throws DaoException {
        convertNewEntity(order, preparedStatement);
    }

    @Override
    public Order parseSingleResultSet(ResultSet resultSet) throws DaoException {
        return convertToEntity(resultSet);
    }

    @Override
    public List<Order> parseListResultSet(ResultSet resultSet) throws DaoException {
        return convertListToEntity(resultSet);
    }

    @Override
    public Long addNew(Order order) throws DaoException {
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(INSERT_NEW_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            convertNewEntity(order, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                return order.getId();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't add new order.");
        }
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws DaoException {
        try (PreparedStatement preparedStatement = ConnectionHolder.getLocalConnection().prepareStatement(DELETE_FROM
                .concat(ORDERS_ID))) {
            preparedStatement.setLong(GENERIC_FIRST_COLUMN, orderId);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new DaoException("Can't delete order.");
        }
    }

    @Override
    public List<Order> orderListByClient(Long clientId) throws DaoException {
        List<Order> orderList = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            preparedStatement.setLong(GENERIC_FIRST_COLUMN, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Can't view order list by client.", e);
        }
        return orderList;
    }

    @Override
    public List<Order> notActiveOrderListByClient(Long clientId) throws DaoException {
        List<Order> orderList = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_FROM_ORDERS_BY_CLIENTS_ID)) {
            preparedStatement.setLong(GENERIC_FIRST_COLUMN, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Can't view not active order list by client.", e);
        }
        return orderList;
    }

    @Override
    public List<Order> activeOrderList() throws DaoException {
        List<Order> orderList = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_ALL_ACTIVE_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Can't view active order list.", e);
        }
        return orderList;
    }

    @Override
    public List<Order> notActiveOrderList() throws DaoException {
        List<Order> orderList = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_ALL_NOT_ACTIVE_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Can't view not active order list.", e);
        }
        return orderList;
    }

    @Override
    public List<Order> accomplishedOrderList() throws DaoException {
        List<Order> orderList = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(SELECT_ALL_ACCOMPLISHED_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Can't view accomplished order list.", e);
        }
        return orderList;
    }

    @Override
    public List<Order> orderListByFilter(OrderFilter orderFilter) throws DaoException {
        DateTime dateTime = new DateTime();
        LocalDate localDate = new LocalDate();
        if (orderFilter.getOrderType() != null) {
            if (StringUtils.equals(orderFilter.getOrderType(), OrderType.NOT_ACTIVE.getTitle())) {
                genericStringBuilder.append(SELECT_ALL_NOT_ACTIVE_ORDERS);
            } else if (StringUtils.equals(orderFilter.getOrderType(), OrderType.ACTIVE.getTitle())) {
                genericStringBuilder.append(SELECT_ALL_ACTIVE_ORDERS);
            } else if (StringUtils.equals(orderFilter.getOrderType(), OrderType.ACCOMPLISHED.getTitle())) {
                genericStringBuilder.append(SELECT_ALL_ACCOMPLISHED_ORDERS);
            } else if (StringUtils.equals(orderFilter.getOrderType(), OrderType.ALL.getTitle())) {
                genericStringBuilder.append(SELECT_FROM)
                        .append(ORDER_TABLE);
            }
        }

        if (orderFilter.getDateValue() != null) {
            if (StringUtils.equals(orderFilter.getDateValue(), DateOption.NO_LIMITS.getTitle())) {
                genericStringBuilder.append(";");
            } else if (StringUtils.equals(orderFilter.getDateValue(), DateOption.BY_TODAY.getTitle())) {
                genericStringBuilder
                        .append(" AND ORD.CREATE_DATE LIKE '")
                        .append(dateTime.getYear())
                        .append("-")
                        .append(dateTime.getMonthOfYear())
                        .append("-")
                        .append(dateTime.getDayOfMonth())
                        .append("%';");
                System.out.println("Год:" + dateTime.getYear() + dateTime.getMonthOfYear() + dateTime.getDayOfMonth());
            } else if (StringUtils.equals(orderFilter.getDateValue(), DateOption.BY_WEEK.getTitle())) {
                genericStringBuilder
                        .append(" AND ORD.CREATE_DATE BETWEEN CAST('")
                        .append(localDate.withDayOfWeek(DateTimeConstants.MONDAY))
                        .append("' AS DATE) AND current_timestamp();");
            } else if (StringUtils.equals(orderFilter.getDateValue(), DateOption.BY_MONTH.getTitle())) {
                genericStringBuilder
                        .append(" AND ORD.CREATE_DATE BETWEEN CAST('")
                        .append(dateTime.getYear())
                        .append("-")
                        .append(dateTime.getMonthOfYear())
                        .append("-01' AS DATE) AND current_timestamp();");
            }
        }

        List<Order> orderList = null;
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(genericStringBuilder.toString())) {
            ResultSet resultSet = preparedStatement.executeQuery();
            orderList = convertListToEntity(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Can't view order list by filter.", e);
        } finally {
            dateTime = null;
            localDate = null;
            genericStringBuilder.setLength(0);
        }
        return orderList;
    }

    @Override
    public void convertNewEntity(Order order, PreparedStatement preparedStatement) throws DaoException {
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
            throw new DaoException("Can't convert new order entity in converter.", e);
        }
    }

    @Override
    public void convertUpdateEntity(Order order, PreparedStatement preparedStatement) throws DaoException {
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
            throw new DaoException("Can't update order entity in converter.", e);
        }
    }

    @Override
    public Order convertToEntity(ResultSet resultSet) throws DaoException {
        Order order = null;
        try {
            while (resultSet.next()) {
                order = new Order();
                order.setId(resultSet.getLong("ORD.ID"));
                order.setClientId(resultSet.getLong("ORD.ID_CLIENT"));
                order.setTitle(resultSet.getString("ORD.TITLE"));
                order.setNote(resultSet.getString("ORD.NOTE"));
                order.setPrice(resultSet.getString("ORD.PRICE"));
                order.setCreateDate(resultSet.getDate("ORD.CREATE_DATE"));
                order.setActive(resultSet.getBoolean("ORD.ACTIVE"));
                order.setBeginAddress(resultSet.getString("ORD.BEGIN_ADDRESS"));
                order.setHouseNumber(resultSet.getString("ORD.HOUSE_NUMBER"));
                order.setPorchNumber(resultSet.getString("ORD.PORCH_NUMBER"));
                order.setOnPerfomance(resultSet.getBoolean("ORD.ON_PERFORMANCE"));
                order.setAccomplished(resultSet.getBoolean("ORD.ACCOMPLISHED"));
                order.setPhone(resultSet.getString("ORD.PHONE"));
                order.setContactName(resultSet.getString("ORD.CONTACT_NAME"));
                order.setCarOption(resultSet.getString("ORD.CAR_OPTION"));
                List orderAddressList = order.getAddressList();
                do {
                    OrderAddress orderAddress = new OrderAddress();
                    orderAddress.setId(resultSet.getLong("ORD_AD.ID"));
                    orderAddress.setId(resultSet.getLong("ORD_AD.ID_ORDER"));
                    orderAddress.setDestinationAddress(resultSet.getString("ORD_AD.DESTINATION_ADDRESS"));
                    orderAddress.setDestinationHouseNumber(resultSet.getString("ORD_AD.DESTINATION_HOUSE_NUMBER"));
                    orderAddress.setDestinationPorchNumber(resultSet.getString("ORD_AD.DESTINATION_PORCH_NUMBER"));
                    orderAddressList.add(orderAddress);
                } while (resultSet.next() && resultSet.getLong("ORD.ID") == order.getId());
                resultSet.previous();
                order.setAddressList(orderAddressList);
                return order;
            }
        } catch (SQLException e) {
            throw new DaoException("Can't convert order to entity in converter.", e);
        }
        return null;
    }

    @Override
    public List<Order> convertListToEntity(ResultSet resultSet) throws DaoException {
        List<Order> orderList = new ArrayList<Order>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("ORD.ID"));
                order.setClientId(resultSet.getLong("ORD.ID_CLIENT"));
                order.setTitle(resultSet.getString("ORD.TITLE"));
                order.setNote(resultSet.getString("ORD.NOTE"));
                order.setPrice(resultSet.getString("ORD.PRICE"));
                order.setCreateDate(resultSet.getDate("ORD.CREATE_DATE"));
                order.setActive(resultSet.getBoolean("ORD.ACTIVE"));
                order.setBeginAddress(resultSet.getString("ORD.BEGIN_ADDRESS"));
                order.setHouseNumber(resultSet.getString("ORD.HOUSE_NUMBER"));
                order.setPorchNumber(resultSet.getString("ORD.PORCH_NUMBER"));
                order.setOnPerfomance(resultSet.getBoolean("ORD.ON_PERFORMANCE"));
                order.setAccomplished(resultSet.getBoolean("ORD.ACCOMPLISHED"));
                order.setPhone(resultSet.getString("ORD.PHONE"));
                order.setContactName(resultSet.getString("ORD.CONTACT_NAME"));
                order.setCarOption(resultSet.getString("ORD.CAR_OPTION"));
                List orderAddressList = order.getAddressList();
                do {
                    OrderAddress orderAddress = new OrderAddress();
                    orderAddress.setId(resultSet.getLong("ORD_AD.ID"));
                    orderAddress.setId(resultSet.getLong("ORD_AD.ID_ORDER"));
                    orderAddress.setDestinationAddress(resultSet.getString("ORD_AD.DESTINATION_ADDRESS"));
                    orderAddress.setDestinationHouseNumber(resultSet.getString("ORD_AD.DESTINATION_HOUSE_NUMBER"));
                    orderAddress.setDestinationPorchNumber(resultSet.getString("ORD_AD.DESTINATION_PORCH_NUMBER"));
                    orderAddressList.add(orderAddress);
                } while (resultSet.next() && resultSet.getLong("ORD.ID") == order.getId());
                resultSet.previous();
                order.setAddressList(orderAddressList);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't convert order list to entities in converter.", e);
        }
        System.out.println(orderList.toString());
        return orderList;
    }
}
