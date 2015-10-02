package com.taxi.service.serviceImpl;

import com.taxi.service.dao.OrderDao;
import com.taxi.service.daoImpl.DaoFactoryImpl;
import com.taxi.service.daoImpl.OrderAddressDaoImpl;
import com.taxi.service.daoImpl.OrderDaoImpl;
import com.taxi.service.entity.Order;
import com.taxi.service.service.OrderService;

import java.util.List;

public class OrderServiceImpl extends GenericServiceImpl<Order, OrderDaoImpl> implements OrderService {

    private OrderDao orderDao = DaoFactoryImpl.getInstance().getOrderDao();
    private OrderAddressDaoImpl orderAddressDao = DaoFactoryImpl.getInstance().getOrderAddressDao();

    OrderServiceImpl() {
        setDao((OrderDaoImpl) orderDao);
    }

    @Override
    public Long addNew(Order order) {
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
