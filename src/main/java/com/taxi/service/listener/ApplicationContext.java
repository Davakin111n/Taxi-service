package com.taxi.service.listener;

import com.taxi.service.daoImpl.ClientDaoImpl;
import com.taxi.service.daoImpl.OrderDaoImpl;
import com.taxi.service.serviceImpl.ClientServiceImpl;
import com.taxi.service.serviceImpl.OrderServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("clientDao", new ClientDaoImpl());
        servletContextEvent.getServletContext().setAttribute("orderDao", new OrderDaoImpl());
        servletContextEvent.getServletContext().setAttribute("clientService", new ClientServiceImpl());
        servletContextEvent.getServletContext().setAttribute("orderService", new OrderServiceImpl());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
