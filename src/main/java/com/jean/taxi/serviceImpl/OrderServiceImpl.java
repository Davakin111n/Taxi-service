package com.jean.taxi.serviceImpl;

import com.jean.taxi.daoImpl.DaoFactoryImpl;
import com.jean.taxi.daoImpl.OrderAddressDaoImpl;
import com.jean.taxi.daoImpl.OrderDaoImpl;
import com.jean.taxi.entity.Identifier;
import com.jean.taxi.entity.Order;
import com.jean.taxi.entity.OrderAddress;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.exception.ServiceException;
import com.jean.taxi.filter.OrderFilter;
import com.jean.taxi.service.OrderService;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl extends GenericServiceImpl<Order, OrderDaoImpl> implements OrderService {
    private final static Logger log = Logger.getLogger(OrderServiceImpl.class);
    private OrderDaoImpl orderDao = (OrderDaoImpl) DaoFactoryImpl.getInstance().getOrderDao();
    private OrderAddressDaoImpl orderAddressDao = DaoFactoryImpl.getInstance().getOrderAddressDao();

    public OrderServiceImpl() {
        log.debug("Setting order DAO.");
        super.setDao(orderDao);
    }

    @Override
    public void addNew(final Order order) throws ServiceException {
        log.debug("Adding new order.");
        TransactionHandlerImpl.execute(new Transaction<Long>() {
            @Override
            public void doTransaction() throws DaoException {
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
    public void update(final Identifier obj) throws ServiceException {
        final Order order = (Order) obj;
        TransactionHandlerImpl.execute(new Transaction<Long>() {
            @Override
            public void doTransaction() throws DaoException {
                orderDao.update(order);
                for (OrderAddress address : order.getAddressList()) {
                    orderAddressDao.update(address);
                }
            }
        });
    }

    @Override
    public void deleteOrder(final Long orderId) throws ServiceException {
        log.debug("Deleting order.");
        TransactionHandlerImpl.execute(new Transaction<Long>() {
            @Override
            public void doTransaction() throws DaoException {
                orderAddressDao.remove(orderId);
                orderDao.remove(orderId);
            }
        });
    }

    @Override
    public void activateOrder(Long orderId) throws ServiceException {
        log.debug("Activating order.");
        try {
            Order order = dao.get(orderId);
            order.setActive(true);
            update(order);
        } catch (DaoException e) {
            throw new ServiceException("Can't activate order.", e);
        }
    }

    @Override
    public List<Order> activeOrderList() throws ServiceException {
        try {
            return dao.activeOrderList();
        } catch (DaoException e) {
            throw new ServiceException("Can't view active order list.", e);
        }
    }

    @Override
    public List<Order> orderListByClient(Long clientId) throws ServiceException {
        try {
            return dao.orderListByClient(clientId);
        } catch (DaoException e) {
            throw new ServiceException("Can't view active order list.", e);
        }
    }

    @Override
    public List<Order> notActiveOrderListByClient(Long clientId) throws ServiceException {
        log.debug("Output not active order list.");
        try {
            return dao.notActiveOrderList();
        } catch (DaoException e) {
            throw new ServiceException("Can't view not active order list by client.", e);
        }
    }

    @Override
    public List<Order> notActiveOrderList() throws ServiceException {
        try {
            return dao.notActiveOrderList();
        } catch (DaoException e) {
            throw new ServiceException("Can't view not active order list.", e);
        }
    }

    @Override
    public List<Order> accomplishedOrderList() throws ServiceException {
        try {
        return dao.accomplishedOrderList();
        } catch (DaoException e) {
            throw new ServiceException("Can't view accomlished order list.", e);
        }
    }

    @Override
    public List<Order> orderListByFilter(OrderFilter orderFilter) throws ServiceException {
        log.debug("Output order list by filter.");
        try {
            return dao.orderListByFilter(orderFilter);
        } catch (DaoException e) {
            throw new ServiceException("Can't view order list by filter.", e);
        }
    }
}
