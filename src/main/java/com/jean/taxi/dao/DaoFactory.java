package com.jean.taxi.dao;

import com.jean.taxi.daoImpl.ClientGrantDaoImpl;
import com.jean.taxi.daoImpl.DaoFactoryImpl;
import com.jean.taxi.daoImpl.OrderAddressDaoImpl;
import com.jean.taxi.daoImpl.ReviewDaoImpl;


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
