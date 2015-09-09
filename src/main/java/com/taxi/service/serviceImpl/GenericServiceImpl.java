package com.taxi.service.serviceImpl;

import com.taxi.service.dao.GenericDao;
import com.taxi.service.entity.Identifier;
import com.taxi.service.service.GenericService;

import java.util.List;

public class GenericServiceImpl<T extends Identifier, D extends GenericDao> implements GenericService {
    @Override
    public Identifier get(Long id) {
        return null;
    }

    @Override
    public boolean isExists(Long id) {
        return false;
    }

    @Override
    public void update(Identifier obj) {

    }

    @Override
    public List listAll() {
        return null;
    }

    @Override
    public Class getEntityClass() {
        return null;
    }
}
