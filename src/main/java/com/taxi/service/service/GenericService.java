package com.taxi.service.service;

import com.taxi.service.entity.Identifier;

import java.util.List;

public interface GenericService<T extends Identifier> {
    T get(long id);

    boolean isExists(long id);

    T save(T obj);

    void delete(T obj);

    List<T> listAll();
}
