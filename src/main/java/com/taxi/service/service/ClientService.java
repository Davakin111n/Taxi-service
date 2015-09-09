package com.taxi.service.service;

import com.taxi.service.entity.User;

import java.util.List;

public interface ClientService {
    User addNew(User user);

    List<User> listAllModerators();

    User getByEmail(String clientEmail);

    List<User> findBySearchRequest(String clientSearchQuery);
}
