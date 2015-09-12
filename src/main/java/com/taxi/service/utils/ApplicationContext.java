package com.taxi.service.utils;

import com.taxi.service.daoImpl.ClientDaoImpl;
import com.taxi.service.serviceImpl.ClientServiceImpl;
import com.taxi.service.serviceImpl.OrderServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("clientDao", new ClientDaoImpl(DataBaseUtil.getConnectionPoolInstance()));
        servletContextEvent.getServletContext().setAttribute("orderDao", new ClientDaoImpl(DataBaseUtil.getConnectionPoolInstance()));
        servletContextEvent.getServletContext().setAttribute("clientService", new ClientServiceImpl());
        servletContextEvent.getServletContext().setAttribute("orderService", new OrderServiceImpl());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
