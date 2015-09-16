package com.taxi.service.service;

import com.taxi.service.entity.User;

import java.util.List;

public interface ClientService {
    User addNew(User user);

    void madeModerator(Long userId);

    void changePassword(Long userId, String password);

    boolean successLogin(String login, String password);

    List<User> listAllModerators();

    List<User> listSimpleUsers();

    User getByEmail(String userEmail);

    List<User> findBySearchRequest(String clientSearchQuery);
}
