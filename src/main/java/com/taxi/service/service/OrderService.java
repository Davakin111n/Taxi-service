package com.taxi.service.service;

import com.taxi.service.entity.Order;

import java.util.List;

public interface OrderService {
    void addNew(Order order);

    void deleteOrder(Long orderId);

    void activateOrder(Long orderId);

    List<Order> orderListByClient(Long clientId);

    List<Order> notActiveOrderListByClient(Long clientId);

    List<Order> activeOrderList();

    List<Order> notActiveOrderList();

}
