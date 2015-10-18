package com.jean.taxi.daoImpl;

import com.jean.taxi.dao.ClientDao;
import com.jean.taxi.dao.DaoFactory;

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
