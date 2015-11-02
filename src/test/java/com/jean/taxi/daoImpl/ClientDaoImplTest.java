package com.jean.taxi.daoImpl;

import com.jean.taxi.dict.ClientType;
import com.jean.taxi.dict.DateOption;
import com.jean.taxi.entity.ClientGrant;
import com.jean.taxi.entity.User;
import com.jean.taxi.filter.ClientFilter;
import com.jean.taxi.serviceImpl.Transaction;
import com.jean.taxi.serviceImpl.TransactionHandlerImpl;
import com.jean.taxi.utils.ConnectionHolder;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.dbunit.annotation.DataSet;

import javax.sql.DataSource;
import java.util.List;

import static com.jean.taxi.serviceImpl.TransactionHandlerImpl.executeTest;
import static org.junit.Assert.assertTrue;

@DataSet
public class ClientDaoImplTest extends UnitilsJUnit4 {

    ClientDaoImpl clientDao = null;
    ClientGrantDaoImpl clientGrantDao = null;

    @TestDataSource
    DataSource dataSource;

    @Before
    public void init() {
        clientDao = new ClientDaoImpl(dataSource);
        clientGrantDao = new ClientGrantDaoImpl(dataSource);
        TransactionHandlerImpl.setDataSource(dataSource);
        clientDao.setCurrentLocalConnection(ConnectionHolder.getLocalConnection());
        clientGrantDao.setCurrentLocalConnection(ConnectionHolder.getLocalConnection());
    }

    @After
    public void destroy() {
        clientDao = null;
        clientGrantDao = null;
    }

    @Test
    public void testAddNew() {
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

    @Test
    public void testListModerators() {
        List moderatorList = clientDao.listAllModerators();
        assertTrue(assertList(moderatorList));
    }

    @Test
    public void testListSimpleUsers() {
        List simpleUsersList = clientDao.listSimpleUsers();
        assertTrue(assertList(simpleUsersList));
    }

    @Test
    public void testBanList() {
        List banList = clientDao.banList();
        assertTrue(assertList(banList));
    }

    @Test
    public void testGetByEmail() {
        String email = "midvey@googlemail.com";
        User user = clientDao.getByEmail(email);
        assertTrue(assertGetByEmail(email, user));
    }

    @Test
    public void testClientListByFilter() {
        ClientFilter clientFilter = new ClientFilter();

    }

    private boolean assertClient(User expected, User current) {
        if (expected.getId() == null) {
            if (current.getId() != null) {
                return false;
            }
        } else if (!StringUtils.equals(Long.valueOf(expected.getId()).toString(), Long.valueOf(current.getId()).toString())) {
            return false;
        }

        if (expected.getPassword() == null) {
            if (current.getPassword() != null) {
                return false;
            }
        } else if (!StringUtils.equals(expected.getPassword(), current.getPassword())) {
            return false;
        }

        if (expected.getEmail() == null) {
            if (current.getEmail() != null) {
                return false;
            }
        } else if (!StringUtils.equals(expected.getEmail(), current.getEmail())) {
            return false;
        }

        if (expected.getClientName() == null) {
            if (current.getClientName() != null) {
                return false;
            }
        } else if (!StringUtils.equals(expected.getClientName(), current.getClientName())) {
            return false;
        }
        return true;
    }

    private boolean assertList(List list) {
        if (list == null) {
            return false;
        } else if (list != null) {
            for (Object obj : list) {
                if (obj != null) {
                    return false;
                }
            }
        }

        if (!list.isEmpty()) {
            for (Object obj : list) {
                if (obj == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean assertGetByEmail(String emailValue, User userByEmail) {
        if (emailValue == null || !StringUtils.isNotEmpty(emailValue)) {
            return false;
        } else if (emailValue != null && StringUtils.isNotEmpty(emailValue)) {
            if (userByEmail == null) {
                return false;
            }
        }

        if (userByEmail != null) {
            if (!StringUtils.isNotEmpty(userByEmail.getEmail())) {
                return false;
            }
        } else if (!StringUtils.equals(emailValue, userByEmail.getEmail())) {
            return false;
        }
        return true;
    }

    private boolean assertClientListByFilter(ClientFilter clientFilter, List clientListByFilter) {
        if (clientFilter == null) {
            return false;
        } else if (clientFilter.getClientType() == null || clientFilter.getDateValue() == null) {
            return false;
        } else if (!StringUtils.isNotEmpty(clientFilter.getClientType()) || !StringUtils.isNotEmpty(clientFilter.getDateValue())) {
            return false;
        }

        if (!assertList(clientListByFilter)) {
            return false;
        } else if (ClientType.values() != null && DateOption.values() != null) {
            if (!ClientType.TITLES.contains(clientFilter.getClientType())) {
                return false;
            }
            if (!DateOption.TITLES.contains(clientFilter.getDateValue())) {
                return false;
            }
        }
        return true;
    }
}
