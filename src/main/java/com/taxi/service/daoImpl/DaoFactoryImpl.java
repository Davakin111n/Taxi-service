package com.taxi.service.daoImpl;

import com.taxi.service.dao.ClientDao;
import com.taxi.service.dao.DaoFactory;
import com.taxi.service.dao.OrderDao;

public class DaoFactoryImpl extends DaoFactory {

    @Override
    public ClientDao getClientDao() {
        return new ClientDaoImpl();
    }

    @Override
    public ClientGrantDaoImpl getClientGrantDao() {
        return new ClientGrantDaoImpl();
    }

    @Override
    public OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }

    @Override
    public OrderAddressDaoImpl getOrderAddressDao() {
        return new OrderAddressDaoImpl();
    }

    @Override
    public ReviewDaoImpl getReviewDao() {
        return new ReviewDaoImpl();
    }
}
