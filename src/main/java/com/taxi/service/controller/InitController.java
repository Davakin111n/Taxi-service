package com.taxi.service.controller;

import com.taxi.service.dao.ClientDao;
import com.taxi.service.dao.OrderDao;
import com.taxi.service.daoImpl.ClientDaoImpl;
import com.taxi.service.daoImpl.OrderDaoImpl;
import com.taxi.service.service.ClientService;
import com.taxi.service.service.OrderService;
import com.taxi.service.serviceImpl.ClientServiceImpl;
import com.taxi.service.serviceImpl.OrderServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitController extends HttpServlet {
    private ClientService clientService;
    private OrderService orderService;
    private ClientDao clientDao;
    private OrderDao orderDao;

    @Override
    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        this.clientDao = (ClientDaoImpl) servletConfig.getServletContext().getAttribute("clientDao");
        this.orderDao = (OrderDaoImpl) servletConfig.getServletContext().getAttribute("orderDao");
        this.clientService = (ClientServiceImpl) servletConfig.getServletContext().getAttribute("clientService");
        this.orderService = (OrderServiceImpl) servletConfig.getServletContext().getAttribute("orderService");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("WEB-INF/pages/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }
}
