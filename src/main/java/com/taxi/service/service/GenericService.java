package com.taxi.service.service;

import com.taxi.service.entity.Identifier;

import java.util.List;

public interface GenericService<T extends Identifier> {
    T get(Long id);

    boolean isExists(Long id);

    void update(final T obj);

    List<T> listAll();

    Class getEntityClass();
}
