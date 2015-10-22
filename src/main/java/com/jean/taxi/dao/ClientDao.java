package com.jean.taxi.dao;

import com.jean.taxi.entity.User;
import com.jean.taxi.filter.ClientFilter;

import java.util.List;

public interface ClientDao {

    List<User> listAllModerators();

    List<User> listSimpleUsers();

    List<User> clientListByFilter(ClientFilter clientFilter);

    List<User> banList();

    User getByEmail(String clientEmail);
}
