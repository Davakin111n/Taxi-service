package com.taxi.service.service;

import com.taxi.service.entity.Order;

import java.util.List;

public interface OrderService {
    Order addNew(Order order);

    void deleteOrder(Long orderId);

    List<Order> orderListByClient(Long clientId);

    List<Order> notActiveOrderListByClient(Long clientId);

    List<Order> notActiveOrderList();
}
