package com.taxi.service.controller;

import com.taxi.service.daoImpl.ClientDaoImpl;
import com.taxi.service.daoImpl.OrderDaoImpl;
import com.taxi.service.daoImpl.ReviewDaoImpl;
import com.taxi.service.dict.Constants;
import com.taxi.service.serviceImpl.ClientServiceImpl;
import com.taxi.service.serviceImpl.OrderServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitController extends HttpServlet {
    private ClientServiceImpl clientService;
    private OrderServiceImpl orderService;
    private ClientDaoImpl clientDao;
    private OrderDaoImpl orderDao;
    private ReviewDaoImpl reviewDao;

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
        this.clientService.setDao(this.clientDao);
        this.orderService.setDao(this.orderDao);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(Constants.INDEX_PATH).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public OrderDaoImpl getOrderDao() {
        return orderDao;
    }

    public ClientServiceImpl getClientService() {
        return clientService;
    }

    public OrderServiceImpl getOrderService() {
        return orderService;
    }

    public ClientDaoImpl getClientDao() {
        return clientDao;
    }
}
