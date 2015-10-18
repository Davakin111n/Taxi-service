package com.jean.taxi.listener;

import com.jean.taxi.serviceImpl.ClientServiceImpl;
import com.jean.taxi.serviceImpl.OrderServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("clientService", new ClientServiceImpl());
        servletContextEvent.getServletContext().setAttribute("orderService", new OrderServiceImpl());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
