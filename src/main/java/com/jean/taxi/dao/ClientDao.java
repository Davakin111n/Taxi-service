package com.jean.taxi.dao;

import com.jean.taxi.entity.User;

import java.util.List;

public interface ClientDao {

    List<User> listAllModerators();

    List<User> listSimpleUsers();

    User getByEmail(String clientEmail);
}
