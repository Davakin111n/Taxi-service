package com.jean.taxi.dao;

import com.jean.taxi.entity.Order;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.filter.OrderFilter;

import java.util.List;

public interface OrderDao {

    void deleteOrder(Long orderId) throws DaoException;

    List<Order> orderListByClient(Long clientId) throws DaoException;

    List<Order> notActiveOrderListByClient(Long clientId) throws DaoException;

    List<Order> activeOrderList() throws DaoException;

    List<Order> notActiveOrderList() throws DaoException;

    List<Order> accomplishedOrderList() throws DaoException;

    List<Order> orderListByFilter(OrderFilter orderFilter) throws DaoException;
}
