package com.jean.taxi.serviceImpl;

import com.jean.taxi.dao.GenericDao;
import com.jean.taxi.entity.Identifier;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.exception.ServiceException;
import com.jean.taxi.service.GenericService;

import java.util.List;

public class GenericServiceImpl<T extends Identifier, D extends GenericDao> extends TransactionHandlerImpl implements GenericService {

    protected D dao;

    public void setDao(D dao) {
        this.dao = dao;
    }

    @Override
    public T get(Long id) throws ServiceException {
        try {
            return (T) dao.get(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't get entity.");
        }
    }

    @Override
    public boolean isExists(Long id) throws ServiceException {
        try {
            return dao.isExists(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't get entity.");
        }
    }

    @Override
    public void update(final Identifier obj) throws ServiceException {
        try {
            dao.update(obj);
        } catch (DaoException e) {
            throw new ServiceException("Can't update entity.");
        }
    }

    @Override
    public List listAll() throws ServiceException {
        try {
            return dao.listAll();
        } catch (DaoException e) {
            throw new ServiceException("Can't list all entities list.");
        }
    }

    @Override
    public Class getEntityClass() {
        return null;
    }
}
