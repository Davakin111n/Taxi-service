package com.taxi.service.dao;

import com.taxi.service.entity.Order;
import com.taxi.service.filter.OrderFilter;

import java.util.List;

public interface OrderDao {
    Order addNew(Order order);

    List<Order> listByFilter(OrderFilter orderFilter);

    List<Order> orderListByClient(Long clientId);

    List<Order> notActiveOrderListByClient(Long clientId);

    List<Order> returnsOrderListByClient(Long clientId);

    List<Order> notActiveOrderList();
}
