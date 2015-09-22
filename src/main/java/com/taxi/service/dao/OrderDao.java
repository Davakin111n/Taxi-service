package com.taxi.service.dao;

import com.taxi.service.entity.Order;

import java.util.List;

public interface OrderDao {
    Order addNew(Order order);

    void deleteOrder(Long orderId);

    List<Order> orderListByClient(Long clientId);

    List<Order> notActiveOrderListByClient(Long clientId);

    List<Order> returnsOrderListByClient(Long clientId);

    List<Order> notActiveOrderList();
}
