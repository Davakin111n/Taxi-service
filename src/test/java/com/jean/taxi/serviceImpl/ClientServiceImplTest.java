package com.jean.taxi.serviceImpl;

import com.jean.taxi.daoImpl.ClientDaoImpl;
import com.jean.taxi.entity.User;
import com.jean.taxi.exception.DaoException;
import com.jean.taxi.exception.ServiceException;
import com.jean.taxi.filter.ClientFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Transactional(TransactionMode.ROLLBACK)
public class ClientServiceImplTest extends UnitilsJUnit4 {
    ClientServiceImpl clientService = new ClientServiceImpl();
    ClientDaoImpl clientDao = mock(ClientDaoImpl.class);
    StringBuilder stringBuilder = new StringBuilder();
    User user = new User();

    @TestDataSource
    DataSource dataSource;

    @Before
    public void createUser() {
        clientDao.setDataSource(dataSource);
        clientService.setDao(clientDao);
        user.setEmail("email");
        user.setAddress("address");
        user.setPassword("password");
        user.setClientName("clientName");
        user.setClientLastName("clientLastName");
        user.setPhone("phone");
        user.setSkype("skype");
    }

    @After
    public void cleanDataBase() {
        try (Connection connection = dataSource.getConnection()) {
            int firstColumn = 1;
            stringBuilder.append("DELETE FROM `CLIENT_GRANT`");
            PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            stringBuilder.setLength(0);
            stringBuilder.append("DELETE FROM `CLIENT`");
            preparedStatement = connection.prepareStatement(stringBuilder.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddNew() throws ServiceException, DaoException {
        clientService.addNew(user);
        verify(clientDao, Mockito.times(0)).addNew(user);
    }

    @Test
    public void testUpdate() throws ServiceException, DaoException {
        clientService.update(user);
        verify(clientDao, Mockito.times(0)).update(user);
    }

    @Test
    public void testListAllModerators() throws ServiceException, DaoException {
        clientService.listAllModerators();
        verify(clientDao).listAllModerators();
    }

    @Test
    public void testListSimpleUsers() throws DaoException, ServiceException {
        clientService.listSimpleUsers();
        verify(clientDao).listSimpleUsers();
    }

    @Test
    public void testClientListByFilter() throws ServiceException, DaoException {
        ClientFilter clientFilter = new ClientFilter();
        clientFilter.setClientType("All");
        clientFilter.setDateValue("No limits");
        clientService.addNew(user);
        clientService.clientListByFilter(clientFilter);
        verify(clientDao).clientListByFilter(clientFilter);
    }

    @Test
    public void testBanList() throws ServiceException, DaoException {
        clientService.banList();
        verify(clientDao).banList();
    }

    @Test
    public void testGetByEmail() throws ServiceException, DaoException {
        clientService.addNew(user);
        clientService.getByEmail("email");
        verify(clientDao).getByEmail("email");
    }
}
