package com.taxi.service.serviceImpl;

import com.taxi.service.daoImpl.ClientDaoImpl;
import com.taxi.service.daoImpl.ClientGrantDaoImpl;
import com.taxi.service.daoImpl.DaoFactoryImpl;
import com.taxi.service.entity.ClientGrant;
import com.taxi.service.entity.Identifier;
import com.taxi.service.entity.User;
import com.taxi.service.service.ClientService;
import com.taxi.service.utils.PasswordUtil;

import java.util.List;

public class ClientServiceImpl extends GenericServiceImpl<User, ClientDaoImpl> implements ClientService {

    private ClientDaoImpl clientDao = (ClientDaoImpl) DaoFactoryImpl.getInstance().getClientDao();
    private ClientGrantDaoImpl clientGrantDao = DaoFactoryImpl.getInstance().getClientGrantDao();

    public ClientServiceImpl() {
        super.setDao(clientDao);
    }

    @Override
    public void addNew(final User user) {
        TransactionHandlerImpl.execute(new Transaction<Long>() {
            @Override
            public void doTransaction() {
                user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
                user.setId(clientDao.addNew(user));
                ClientGrant clientGrant = user.getClientGrant();
                clientGrant.setClientId(clientGrantDao.addNew(clientGrant));
                clientGrantDao.addNew(clientGrant);
            }
        });
    }

    @Override
    public void update(final Identifier obj) {
        final User user = (User) obj;
        TransactionHandlerImpl.execute(new Transaction<Long>() {
            @Override
            public void doTransaction() {
                clientDao.update(user);
                clientGrantDao.update(user.getClientGrant());
            }
        });
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
        ClientGrant clientGrant = user.getClientGrant();
        clientGrant.setModerator(true);
        dao.update(user);
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
}
