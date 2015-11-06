package com.jean.taxi.serviceImpl;

import com.jean.taxi.daoImpl.OrderDaoImpl;
import com.jean.taxi.entity.Order;
import com.jean.taxi.entity.OrderAddress;
import com.jean.taxi.filter.OrderFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrderServiceImplTest extends UnitilsJUnit4 {
    OrderServiceImpl orderService = new OrderServiceImpl();
    OrderDaoImpl orderDao = mock(OrderDaoImpl.class);
    StringBuilder stringBuilder = new StringBuilder();
    Order order = new Order();

    @TestDataSource
    DataSource dataSource;

    @Before
    public void init() {
        orderDao.setDataSource(dataSource);
        orderService.setDao(orderDao);
        order.setBeginAddress("beginAddress");
        order.setHouseNumber("houseNumber");
        order.setPorchNumber("porchNumber");
        order.setPhone("phone");
        order.setContactName("contactName");
        order.setCarOption("option");
        order.setNote("note");
        List list = order.getAddressList();
        OrderAddress orderAddress = new OrderAddress();
        orderAddress.setDestinationAddress("destAddress");
        orderAddress.setDestinationHouseNumber("destHouseNumber");
        orderAddress.setDestinationPorchNumber("destPorchNumber");
        list.add(orderAddress);
        order.setAddressList(list);
    }

    @After
    public void cleanDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            int firstColumn = 1;
            stringBuilder.append("DELETE FROM `ORDER_ADDRESS`");
            PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            stringBuilder.setLength(0);
            stringBuilder.append("DELETE FROM `ORDER`");
            preparedStatement = connection.prepareStatement(stringBuilder.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddNew() {
        orderService.addNew(order);
        verify(orderDao, Mockito.times(0)).addNew(order);
    }

    @Test
    public void testUpdate() {
        orderService.update(order);
        verify(orderDao, Mockito.times(0)).update(order);
    }

    @Test
    public void testDeleteOrder() {
        orderService.addNew(order);
        orderService.deleteOrder(order.getId());
        verify(orderDao, Mockito.times(0)).deleteOrder(order.getId());
    }

    @Test
    public void testActiveOrderList() {
        orderService.activeOrderList();
        verify(orderDao).activeOrderList();
    }

    @Test
    public void testNotActiveOrderList() {
        orderService.notActiveOrderList();
        verify(orderDao).notActiveOrderList();
    }

    @Test
    public void testAccomplishedOrderList() {
        orderService.accomplishedOrderList();
        verify(orderDao).accomplishedOrderList();
    }

    @Test
    public void testOrderListByFilter() {
        OrderFilter orderFilter = new OrderFilter("All", "No limits");
        orderService.orderListByFilter(orderFilter);
        verify(orderDao).orderListByFilter(orderFilter);
    }
}
