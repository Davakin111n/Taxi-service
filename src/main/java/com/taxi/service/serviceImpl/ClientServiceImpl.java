package com.taxi.service.serviceImpl;

import com.taxi.service.daoImpl.ClientDaoImpl;
import com.taxi.service.entity.User;
import com.taxi.service.service.ClientService;

import java.util.List;

public class ClientServiceImpl extends GenericServiceImpl<User, ClientDaoImpl> implements ClientService {
    @Override
    public User addNew(User user) {
        return null;
    }

    @Override
    public List<User> listAllModerators() {
        return null;
    }

    @Override
    public User getByEmail(String clientEmail) {
        return null;
    }

    @Override
    public List<User> findBySearchRequest(String clientSearchQuery) {
        return null;
    }
}
