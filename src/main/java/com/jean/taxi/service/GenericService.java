package com.jean.taxi.service;

import com.jean.taxi.entity.Identifier;
import com.jean.taxi.exception.ServiceException;

import java.util.List;

public interface GenericService<T extends Identifier> {
    T get(Long id) throws ServiceException;

    boolean isExists(Long id) throws ServiceException;

    void update(final T obj) throws ServiceException;

    List<T> listAll() throws ServiceException;

    Class getEntityClass();
}
