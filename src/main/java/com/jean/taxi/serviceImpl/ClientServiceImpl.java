package com.jean.taxi.serviceImpl;

import com.jean.taxi.daoImpl.ClientDaoImpl;
import com.jean.taxi.daoImpl.ClientGrantDaoImpl;
import com.jean.taxi.daoImpl.DaoFactoryImpl;
import com.jean.taxi.entity.ClientGrant;
import com.jean.taxi.entity.Identifier;
import com.jean.taxi.entity.User;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.exception.ServiceException;
import com.jean.taxi.filter.ClientFilter;
import com.jean.taxi.service.ClientService;
import com.jean.taxi.utils.PasswordUtil;
import org.apache.log4j.Logger;

import java.util.List;

public class ClientServiceImpl extends GenericServiceImpl<User, ClientDaoImpl> implements ClientService {
    private final static Logger log = Logger.getLogger(ClientServiceImpl.class);
    private ClientDaoImpl clientDao = (ClientDaoImpl) DaoFactoryImpl.getInstance().getClientDao();
    private ClientGrantDaoImpl clientGrantDao = DaoFactoryImpl.getInstance().getClientGrantDao();

    public ClientServiceImpl() {
        log.debug("Setting client DAO.");
        super.setDao(clientDao);
    }

    @Override
    public void addNew(final User user) throws ServiceException {
        log.debug("Adding new user.");
        execute(new Transaction<Long>() {
            @Override
            public void doTransaction() throws DaoException {
                user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
                user.setId(clientDao.addNew(user));
                ClientGrant clientGrant = user.getClientGrant();
                clientGrant.setClientId(user.getId());
                clientGrant.setActive(true);
                clientGrantDao.addNew(clientGrant);
            }
        });
    }

    @Override
    public void update(final Identifier obj) throws ServiceException {
        final User user = (User) obj;
        execute(new Transaction<Long>() {
            @Override
            public void doTransaction() throws DaoException {
                clientDao.update(user);
                clientGrantDao.update(user.getClientGrant());
            }
        });
    }

    @Override
    public boolean successLogin(String email, String password) {
        log.debug("Checking user login information.");
        try {
            User user = dao.getByEmail(email);
            if (user != null) {
                return PasswordUtil.encryptPassword(password).equals(user.getPassword());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> listAllModerators() throws ServiceException {
        try {
            return dao.listAllModerators();
        } catch (DaoException e) {
            throw new ServiceException("Couldn't view moderator list.", e);
        }
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
    public void madeSimpleUser(Long moderatorId) throws ServiceException {
        try {
            User user = dao.get(moderatorId);
            ClientGrant clientGrant = user.getClientGrant();
            clientGrant.setModerator(false);
            user.setClientGrant(clientGrant);
            update(user);
        } catch (DaoException e) {
            throw new ServiceException("Can't made client grant to simple status.");
        }
    }

    @Override
    public void banUser(Long userId) throws ServiceException {
        try {
            User user = dao.get(userId);
            ClientGrant clientGrant = user.getClientGrant();
            clientGrant.setActive(false);
            user.setClientGrant(clientGrant);
            update(user);
        } catch (DaoException e) {
            throw new ServiceException("Can't ban client.");
        }
    }

    @Override
    public void deleteBanUser(Long userId) throws ServiceException {
        try {
            User user = dao.get(userId);
            ClientGrant clientGrant = user.getClientGrant();
            clientGrant.setActive(true);
            user.setClientGrant(clientGrant);
            update(user);
        } catch (DaoException e) {
            throw new ServiceException("Can't delete ban from user.");
        }
    }

    @Override
    public void changePassword(Long userId, final String password) throws ServiceException {
        log.debug("Changing password to user.");
        try {
            final User user = dao.get(userId);
            if (user != null) {
                execute(new Transaction<Long>() {
                    @Override
                    public void doTransaction() throws DaoException {
                        user.setPassword(PasswordUtil.encryptPassword(password));
                        clientDao.update(user);
                    }
                });
            } else {
                throw new ServiceException("Can't change password - client is empty or null.");
            }
        } catch (DaoException e) {
            throw new ServiceException("Can't change password.", e);
        }
    }

    @Override
    public List<User> listSimpleUsers() throws ServiceException {
        try {
            return dao.listSimpleUsers();
        } catch (DaoException e) {
            throw new ServiceException("Couldn't view simple users list.");
        }
    }

    @Override
    public List<User> clientListByFilter(ClientFilter clientFilter) throws ServiceException {
        try {
            return dao.clientListByFilter(clientFilter);
        } catch (DaoException e) {
            throw new ServiceException("Couldn't view client list by filter.");
        }
    }

    @Override
    public List<User> banList() throws ServiceException {
        try {
            return dao.banList();
        } catch (DaoException e) {
            throw new ServiceException("Couldn't view client ban list.");
        }
    }

    @Override
    public User getByEmail(String clientEmail) throws ServiceException {
        log.debug("Getting user by email.");
        try {
            return dao.getByEmail(clientEmail);
        } catch (DaoException e) {
            throw new ServiceException("Can't get client by email.");
        }
    }
}
