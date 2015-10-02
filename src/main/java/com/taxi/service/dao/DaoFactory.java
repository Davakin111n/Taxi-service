package com.taxi.service.dao;

import com.taxi.service.daoImpl.ClientGrantDaoImpl;
import com.taxi.service.daoImpl.DaoFactoryImpl;
import com.taxi.service.daoImpl.OrderAddressDaoImpl;
import com.taxi.service.daoImpl.ReviewDaoImpl;


public abstract class DaoFactory {

    private static DaoFactory instance = null;

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            try {
                instance = DaoFactoryImpl.class.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public abstract ClientDao getClientDao();

    public abstract ClientGrantDaoImpl getClientGrantDao();

    public abstract OrderDao getOrderDao();

    public abstract OrderAddressDaoImpl getOrderAddressDao();

    public abstract ReviewDaoImpl getReviewDao();
}
