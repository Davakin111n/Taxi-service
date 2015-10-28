package com.jean.taxi.daoImpl;

import com.jean.taxi.entity.ClientGrant;
import com.jean.taxi.entity.User;
import com.jean.taxi.serviceImpl.Transaction;
import com.jean.taxi.serviceImpl.TransactionHandlerImpl;
import com.jean.taxi.utils.ConnectionHolder;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.dbunit.annotation.DataSet;

import javax.sql.DataSource;

import static com.jean.taxi.serviceImpl.TransactionHandlerImpl.executeTest;
import static org.junit.Assert.assertTrue;

@DataSet
public class ClientDaoImplTest extends UnitilsJUnit4 {

    ClientDaoImpl clientDao = new ClientDaoImpl();
    ClientGrantDaoImpl clientGrantDao = new ClientGrantDaoImpl();

    @TestDataSource
    DataSource dataSource;

    @Test
    public void testAddNew() {
        clientDao.setDataSource(dataSource);
        clientGrantDao.setDataSource(dataSource);
        TransactionHandlerImpl.setDataSource(dataSource);
        clientDao.setCurrentLocalConnection(ConnectionHolder.getLocalConnection());
        clientGrantDao.setCurrentLocalConnection(ConnectionHolder.getLocalConnection());
        final User user = new User();
        user.setEmail("email");
        user.setAddress("address");
        user.setPassword("password");
        user.setClientName("clientName");
        user.setClientLastName("clientLastName");
        user.setPhone("phone");
        final ClientGrant clientGrant = user.getClientGrant();
        clientGrant.setClientId(user.getId());
        clientGrant.setActive(true);
        user.setClientGrant(clientGrant);
        executeTest(new Transaction<Long>() {
            @Override
            public void doTransaction() throws Exception {
                Long clientId = clientDao.addNew(user);
                user.setId(clientId);
                clientGrantDao.addNew(user.getClientGrant());
                assertTrue(assertClient(clientDao.get(clientId), user));
            }
        });
    }

    private boolean assertClient(User expected, User current) {
        if (expected.getId() == null) {
            if (current.getId() != null) {
                return false;
            }
        } else if (!expected.getId().equals(current.getId())) {
            return false;
        }

        if (expected.getPassword() == null) {
            if (current.getPassword() != null) {
                return false;
            }
        } else if (!expected.getPassword().equals(expected.getPassword())) {
            return false;
        }

        if (expected.getEmail() == null) {
            if (current.getEmail() != null) {
                return false;
            }
        } else if (!expected.getEmail().equals(expected.getEmail())) {
            return false;
        }

        if (expected.getClientName() == null) {
            if (current.getClientName() != null) {
                return false;
            }
        } else if (!expected.getClientName().equals(expected.getClientName())) {
            return false;
        }
        return true;
    }
}
