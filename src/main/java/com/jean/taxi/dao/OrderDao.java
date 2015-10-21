package com.jean.taxi.dao;

import com.jean.taxi.entity.Order;
import com.jean.taxi.filter.OrderFilter;

import java.util.List;

public interface OrderDao {

    void deleteOrder(Long orderId);

    List<Order> orderListByClient(Long clientId);

    List<Order> notActiveOrderListByClient(Long clientId);

    List<Order> activeOrderList();

    List<Order> notActiveOrderList();

    List<Order> accomplishedOrderList();

    List<Order> orderListByFilter(OrderFilter orderFilter);
}
