package com.jean.taxi.serviceImpl;

import com.jean.taxi.daoImpl.ClientDaoImpl;
import com.jean.taxi.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Transactional(TransactionMode.ROLLBACK)
public class ClientServiceImplTest extends UnitilsJUnit4 {
    ClientServiceImpl clientService = new ClientServiceImpl();
    ClientDaoImpl clientDao = mock(ClientDaoImpl.class);
    User user = new User();

    @TestDataSource
    DataSource dataSource;

    @Before
    public void createUser() {
        clientDao.setDataSource(dataSource);
        user.setEmail("email");
        user.setAddress("address");
        user.setPassword("password");
        user.setClientName("clientName");
        user.setClientLastName("clientLastName");
        user.setPhone("phone");
        user.setSkype("skype");
    }

    @Test
    public void testAddNew() {
        clientService.setDao(clientDao);
        clientService.addNew(user);
        verify(clientDao, Mockito.times(0)).addNew(user);
    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testSuccessLogin() {

    }

    @Test
    public void testListAllModerators() {

    }

    @Test
    public void testMadeModerator() {

    }

    @Test
    public void testMadeSimpleUser() {

    }


    @Test
    public void testBanUser() {

    }

    @Test
    public void testDeleteBanUser() {

    }

    @Test
    public void testChangePassword() {

    }

    @Test
    public void testListSimpleUsers() {

    }

    @Test
    public void testClientListByFilter() {

    }

    @Test
    public void testBanList() {

    }

    @Test
    public void testGetByEmail() {

    }

    @After
    public void cleanDataBase() {
        try (PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("DELETE FROM ?")) {
            int firstColumn = 1;
            preparedStatement.setString(firstColumn, "`client_grant`");
            preparedStatement.addBatch();
            preparedStatement.setString(firstColumn, "`client`");
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
