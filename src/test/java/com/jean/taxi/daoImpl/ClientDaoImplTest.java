package com.jean.taxi.daoImpl;

import com.jean.taxi.utils.DataBaseUtil;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;

import javax.sql.DataSource;

public class ClientDaoImplTest extends UnitilsJUnit4 {
    ClientDaoImpl clientDao = new ClientDaoImpl();

    @TestDataSource
    DataSource dataSource;

    @Test
    public void testAddNew() {
        dataSource = DataBaseUtil.connectionPool;
    }
}
