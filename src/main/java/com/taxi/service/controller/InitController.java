package com.taxi.service.controller;

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

    private static ClientServiceImpl clientService;
    private static OrderServiceImpl orderService;

    @Override
    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        clientService = (ClientServiceImpl) servletConfig.getServletContext().getAttribute("clientService");
        orderService = (OrderServiceImpl) servletConfig.getServletContext().getAttribute("orderService");
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

    public static ClientServiceImpl getClientService() {
        return clientService;
    }

    public static OrderServiceImpl getOrderService() {
        return orderService;
    }
}
