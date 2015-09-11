package com.taxi.service.serviceImpl;

import com.taxi.service.dao.GenericDao;
import com.taxi.service.entity.Identifier;
import com.taxi.service.service.GenericService;

import java.util.List;

public class GenericServiceImpl<T extends Identifier, D extends GenericDao> implements GenericService {

    protected D dao;

    public void setDao(D dao) {
        this.dao = dao;
    }

    @Override
    public Identifier get(Long id) {
        return dao.get(id);
    }

    @Override
    public boolean isExists(Long id) {
        return dao.isExists(id);
    }

    @Override
    public void update(Identifier obj) {
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
