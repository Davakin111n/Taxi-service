package com.jean.taxi.daoImpl;

import com.jean.taxi.entity.Order;
import com.jean.taxi.entity.OrderAddress;
import com.jean.taxi.filter.OrderFilter;
import com.jean.taxi.serviceImpl.Transaction;
import com.jean.taxi.serviceImpl.TransactionHandlerImpl;
import com.jean.taxi.utils.ConnectionHolder;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.dbunit.annotation.DataSet;

import javax.sql.DataSource;
import java.util.List;

import static com.jean.taxi.serviceImpl.TransactionHandlerImpl.executeTest;
import static org.junit.Assert.assertTrue;

@Transactional(TransactionMode.ROLLBACK)
@DataSet
public class OrderDaoImplTest extends UnitilsJUnit4 {
    @TestDataSource
    DataSource dataSource;

    OrderDaoImpl orderDao = null;
    OrderAddressDaoImpl orderAddressDao = null;

    @Before
    public void init() {
        orderDao = new OrderDaoImpl(dataSource);
        orderAddressDao = new OrderAddressDaoImpl(dataSource);
        TransactionHandlerImpl.setDataSource(dataSource);
        orderDao.setCurrentLocalConnection(ConnectionHolder.getLocalConnection());
        orderAddressDao.setCurrentLocalConnection(ConnectionHolder.getLocalConnection());
    }

    @After
    public void destroy() {
        orderDao = null;
        orderAddressDao = null;
    }

    @Test
    public void testAddNew() {
        final Order order = new Order();
        order.setBeginAddress("beginAddress");
        order.setHouseNumber("houseNumber");
        order.setPorchNumber("porchNumber");
        order.setPhone("phone");
        order.setContactName("contactName");
        order.setCarOption("option");
        order.setNote("note");
        List list = order.getAddressList();
        OrderAddress orderAddress = new OrderAddress();
        orderAddress.setDestinationAddress("destAddress");
        orderAddress.setDestinationHouseNumber("destHouseNumber");
        orderAddress.setDestinationPorchNumber("destPorchNumber");
        list.add(orderAddress);
        order.setAddressList(list);
        executeTest(new Transaction<Long>() {
            @Override
            public void doTransaction() throws Exception {
                Long orderId = orderDao.addNew(order);
                order.setId(orderId);
                for (OrderAddress address : order.getAddressList()) {
                    address.setOrderId(orderId);
                    orderAddressDao.addNew(address);
                }
                assertTrue(assertOrder(orderDao.get(orderId), order));
            }
        });
    }

    @Test
    public void testDeleteOrder() {

    }

    @Test
    public void testOrderListByClient() {

    }

    @Test
    public void testNotActiveOrderListByClient() {

    }

    @Test
    public void testActiveOrderList() {

    }

    @Test
    public void testNotActiveOrderList() {

    }

    @Test
    public void testAccomplishedOrderList() {

    }

    @Test
    public void testOrderListByFilter() {

    }

    private boolean assertOrder(Order expected, Order current) {
        if (expected == null || current == null) {
            return false;
        }

        if (expected.getId() == null) {
            if (current.getId() != null) {
                return false;
            }
        } else if (!StringUtils.equals(Long.valueOf(expected.getId()).toString(), Long.valueOf(current.getId()).toString())) {
            return false;
        }

        if (expected.equals(current)) {
            return false;
        } else if (expected != null && current != null) {
            if (!StringUtils.isNotEmpty(expected.getTitle())
                    || !StringUtils.isNotEmpty(expected.getPhone())
                    || !StringUtils.isNotEmpty(expected.getContactName())) {
                return false;
            }
            if (!StringUtils.isNotEmpty(current.getTitle())
                    || !StringUtils.isNotEmpty(current.getPhone())
                    || !StringUtils.isNotEmpty(current.getContactName())) {
                return false;
            }
        }

        if (expected.getPhone() == null) {
            if (current.getPhone() != null) {
                return false;
            }
        } else if (!StringUtils.equals(expected.getPhone(), current.getPhone())) {
            return false;
        }

        if (expected.getContactName() == null) {
            if (current.getContactName() != null) {
                return false;
            }
        } else if (!StringUtils.equals(expected.getContactName(), current.getContactName())) {
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

    private boolean assertOrderListByFilter(OrderFilter orderFilter, List orderListByFilter) {
        return true;
    }
}
