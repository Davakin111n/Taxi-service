package com.taxi.service.serviceImpl;

import com.taxi.service.daoImpl.DaoFactoryImpl;
import com.taxi.service.daoImpl.OrderAddressDaoImpl;
import com.taxi.service.daoImpl.OrderDaoImpl;
import com.taxi.service.entity.Identifier;
import com.taxi.service.entity.Order;
import com.taxi.service.entity.OrderAddress;
import com.taxi.service.service.OrderService;

import java.util.List;

public class OrderServiceImpl extends GenericServiceImpl<Order, OrderDaoImpl> implements OrderService {

    private OrderDaoImpl orderDao = (OrderDaoImpl) DaoFactoryImpl.getInstance().getOrderDao();
    private OrderAddressDaoImpl orderAddressDao = DaoFactoryImpl.getInstance().getOrderAddressDao();

    public OrderServiceImpl() {
        super.setDao(orderDao);
    }

    @Override
    public void addNew(final Order order) {
        TransactionHandlerImpl.execute(new Transaction<Long>() {
            @Override
            public void doTransaction() {
                Long orderId = orderDao.addNew(order);
                order.setId(orderId);
                for (OrderAddress address : order.getAddressList()) {
                    address.setOrderId(orderId);
                    orderAddressDao.addNew(address);
                }
            }
        });
    }

    @Override
    public void update(final Identifier obj) {
        final Order order = (Order) obj;
        TransactionHandlerImpl.execute(new Transaction<Long>() {
            @Override
            public void doTransaction() {
                orderDao.update(order);
                for (OrderAddress address : order.getAddressList()) {
                    orderAddressDao.update(address);
                }
            }
        });
    }

    @Override
    public void deleteOrder(final Long orderId) {
        TransactionHandlerImpl.execute(new Transaction<Long>() {
            @Override
            public void doTransaction() {
                orderAddressDao.remove(orderId);
                orderDao.remove(orderId);
            }
        });
    }

    @Override
    public void activateOrder(Long orderId) {
        Order order = dao.get(orderId);
        order.setActive(true);
        dao.update(order);
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
