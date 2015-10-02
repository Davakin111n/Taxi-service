package com.taxi.service.daoImpl;

import com.taxi.service.dao.ClientDao;
import com.taxi.service.dao.DaoFactory;

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
    public OrderDaoImpl getOrderDao() {
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
