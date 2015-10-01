package com.taxi.service.service;

import com.taxi.service.entity.User;

import java.util.List;

public interface ClientService {
    Long addNew(User user);

    void madeModerator(Long userId) throws Exception;

    void changePassword(Long userId, String password) throws Exception;

    boolean successLogin(String login, String password);

    List<User> listAllModerators();

    List<User> listSimpleUsers();

    User getByEmail(String userEmail);
}
