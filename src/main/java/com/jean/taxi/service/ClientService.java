package com.jean.taxi.service;

import com.jean.taxi.entity.User;

import java.util.List;

public interface ClientService {
    void addNew(User user);

    void madeModerator(Long userId) throws Exception;

    void changePassword(Long userId, String password) throws Exception;

    boolean successLogin(String login, String password);

    List<User> listAllModerators();

    List<User> listSimpleUsers();

    User getByEmail(String userEmail);
}
