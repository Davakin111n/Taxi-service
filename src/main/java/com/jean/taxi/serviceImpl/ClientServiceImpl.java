package com.jean.taxi.serviceImpl;

import com.jean.taxi.daoImpl.ClientDaoImpl;
import com.jean.taxi.daoImpl.ClientGrantDaoImpl;
import com.jean.taxi.daoImpl.DaoFactoryImpl;
import com.jean.taxi.entity.ClientGrant;
import com.jean.taxi.entity.Identifier;
import com.jean.taxi.entity.User;
import com.jean.taxi.service.ClientService;
import com.jean.taxi.utils.PasswordUtil;

import java.util.List;

public class ClientServiceImpl extends GenericServiceImpl<User, ClientDaoImpl> implements ClientService {

    private ClientDaoImpl clientDao = (ClientDaoImpl) DaoFactoryImpl.getInstance().getClientDao();
    private ClientGrantDaoImpl clientGrantDao = DaoFactoryImpl.getInstance().getClientGrantDao();

    public ClientServiceImpl() {
        super.setDao(clientDao);
    }

    @Override
    public void addNew(final User user) {
        execute(new Transaction<Long>() {
            @Override
            public void doTransaction() {
                user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
                user.setId(clientDao.addNew(user));
                ClientGrant clientGrant = user.getClientGrant();
                clientGrant.setClientId(user.getId());
                clientGrantDao.addNew(clientGrant);
            }
        });
    }

    @Override
    public void update(final Identifier obj) {
        final User user = (User) obj;
        execute(new Transaction<Long>() {
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
        user.setClientGrant(clientGrant);
        update(user);
    }

    @Override
    public void changePassword(Long userId, final String password) throws Exception {
        final User user = dao.get(userId);
        execute(new Transaction<Long>() {
            @Override
            public void doTransaction() throws Exception {
                if (user != null) {
                    user.setPassword(PasswordUtil.encryptPassword(password));
                    clientDao.update(user);
                } else {
                    throw new Exception();
                }
            }
        });
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
