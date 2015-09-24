package com.taxi.service.serviceImpl;

import com.taxi.service.daoImpl.ClientDaoImpl;
import com.taxi.service.entity.User;
import com.taxi.service.service.ClientService;
import com.taxi.service.utils.PasswordUtil;

import java.util.List;

public class ClientServiceImpl extends GenericServiceImpl<User, ClientDaoImpl> implements ClientService {
    @Override
    public User addNew(User user) {
        user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
        return dao.addNew(user);
    }

    @Override
    public boolean successLogin(String email, String password) {
        User user = dao.getByEmail(email);
        if (user != null) {
            return PasswordUtil.encryptPassword(password).equals(user.getPassword());
        }
        return false;
    }

    @Override
    public List<User> listAllModerators() {
        return dao.listAllModerators();
    }

    @Override
    public void madeModerator(Long userId) throws Exception {
        User user = dao.get(userId);
        if (user != null) {
            user.setModerator(true);
            dao.update(user);
        } else {
            throw new Exception();
        }
    }

    @Override
    public void changePassword(Long userId, String password) throws Exception {
        User user = dao.get(userId);
        if (user != null) {
            user.setPassword(PasswordUtil.encryptPassword(password));
            dao.update(user);
        } else {
            throw new Exception();
        }
    }

    @Override
    public List<User> listSimpleUsers() {
        return dao.listSimpleUsers();
    }

    @Override
    public User getByEmail(String clientEmail) {
        return dao.getByEmail(clientEmail);
    }

    @Override
    public List<User> findBySearchRequest(String clientSearchQuery) {
        return dao.findBySearchRequest(clientSearchQuery);
    }
}
