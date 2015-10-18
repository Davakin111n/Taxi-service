package com.jean.taxi.serviceImpl;

import com.jean.taxi.dao.GenericDao;
import com.jean.taxi.entity.Identifier;
import com.jean.taxi.service.GenericService;

import java.util.List;

public class GenericServiceImpl<T extends Identifier, D extends GenericDao> extends TransactionHandlerImpl implements GenericService {

    protected D dao;

    public void setDao(D dao) {
        this.dao = dao;
    }

    @Override
    public T get(Long id) {
        return (T) dao.get(id);
    }

    @Override
    public boolean isExists(Long id) {
        return dao.isExists(id);
    }

    @Override
    public void update(final Identifier obj) {
        dao.update(obj);
    }

    @Override
    public List listAll() {
        return dao.listAll();
    }

    @Override
    public Class getEntityClass() {
        return null;
    }
}
