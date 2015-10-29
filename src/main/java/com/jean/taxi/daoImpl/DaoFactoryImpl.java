package com.jean.taxi.daoImpl;

import com.jean.taxi.dao.ClientDao;
import com.jean.taxi.dao.DaoFactory;
import com.jean.taxi.utils.DataBaseUtil;

public class DaoFactoryImpl extends DaoFactory {

    @Override
    public ClientDao getClientDao() {
        return new ClientDaoImpl(DataBaseUtil.connectionPool);
    }

    @Override
    public ClientGrantDaoImpl getClientGrantDao() {
        return new ClientGrantDaoImpl(DataBaseUtil.connectionPool);
    }

    @Override
    public OrderDaoImpl getOrderDao() {
        return new OrderDaoImpl(DataBaseUtil.connectionPool);
    }

    @Override
    public OrderAddressDaoImpl getOrderAddressDao() {
        return new OrderAddressDaoImpl(DataBaseUtil.connectionPool);
    }

    @Override
    public ReviewDaoImpl getReviewDao() {
        return new ReviewDaoImpl();
    }
}
