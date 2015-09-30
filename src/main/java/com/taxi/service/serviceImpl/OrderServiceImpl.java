package com.taxi.service.serviceImpl;

import com.taxi.service.daoImpl.OrderDaoImpl;
import com.taxi.service.entity.Order;
import com.taxi.service.service.OrderService;

import java.util.List;

public class OrderServiceImpl extends GenericServiceImpl<Order, OrderDaoImpl> implements OrderService {

    @Override
    public Order addNew(Order order) {
        return dao.addNew(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        dao.deleteOrder(orderId);
    }

    @Override
    public void activateOrder(Long orderId) {

    }

    @Override
    public List<Order> orderListByClient(Long clientId) {
        return dao.orderListByClient(clientId);
    }

    @Override
    public List<Order> notActiveOrderListByClient(Long clientId) {
        return dao.notActiveOrderList();
    }

    @Override
    public List<Order> notActiveOrderList() {
        return dao.notActiveOrderList();
    }
}
