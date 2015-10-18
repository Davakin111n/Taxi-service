package com.jean.taxi.service;

import com.jean.taxi.entity.Identifier;

import java.util.List;

public interface GenericService<T extends Identifier> {
    T get(Long id);

    boolean isExists(Long id);

    void update(final T obj);

    List<T> listAll();

    Class getEntityClass();
}
