package com.taxi.service.dao;

import com.taxi.service.entity.User;

import java.util.List;

public interface ClientDao {
    User addNew(User user);

    List<User> listAllModerators();

    User getByEmail(String clientEmail);

    List<User> findBySearchRequest(String clientSearchQuery);
}
