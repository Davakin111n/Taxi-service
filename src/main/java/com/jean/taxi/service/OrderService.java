package com.jean.taxi.service;

import com.jean.taxi.entity.Order;
import com.jean.taxi.exception.ServiceException;
import com.jean.taxi.filter.OrderFilter;

import java.util.List;

public interface OrderService {
    void addNew(Order order) throws ServiceException;

    void deleteOrder(Long orderId) throws ServiceException;

    void activateOrder(Long orderId) throws ServiceException;

    List<Order> orderListByClient(Long clientId) throws ServiceException;

    List<Order> notActiveOrderListByClient(Long clientId) throws ServiceException;

    List<Order> activeOrderList() throws ServiceException;

    List<Order> notActiveOrderList() throws ServiceException;

    List<Order> accomplishedOrderList() throws ServiceException;

    List<Order> orderListByFilter(OrderFilter orderFilter) throws ServiceException;
}
