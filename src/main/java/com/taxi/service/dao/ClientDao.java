package com.taxi.service.dao;

import com.taxi.service.entity.User;

import java.util.List;

public interface ClientDao {

    List<User> listAllModerators();

    List<User> listSimpleUsers();

    User getByEmail(String clientEmail);
}
