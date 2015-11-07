package com.jean.taxi.dao;

import com.jean.taxi.entity.User;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.filter.ClientFilter;

import java.util.List;

public interface ClientDao {

    List<User> listAllModerators() throws DaoException;

    List<User> listSimpleUsers() throws DaoException;

    List clientListByFilter(ClientFilter clientFilter) throws DaoException;

    List<User> banList() throws DaoException;

    User getByEmail(String clientEmail) throws DaoException;
}
