package com.jean.taxi.service;

import com.jean.taxi.entity.User;
import com.jean.taxi.exception.ServiceException;
import com.jean.taxi.filter.ClientFilter;

import java.util.List;

public interface ClientService {

    void addNew(User user) throws ServiceException;

    void madeModerator(Long userId) throws Exception;

    void madeSimpleUser(Long moderatorId) throws ServiceException;

    void banUser(Long userId) throws ServiceException;

    void deleteBanUser(Long userId) throws ServiceException;

    void changePassword(Long userId, String password) throws Exception;

    boolean successLogin(String login, String password);

    List<User> listAllModerators() throws ServiceException;

    List<User> listSimpleUsers() throws ServiceException;

    List<User> clientListByFilter(ClientFilter clientFilter) throws ServiceException;

    List<User> banList() throws ServiceException;

    User getByEmail(String userEmail) throws ServiceException;
}
