package com.taxi.service.controller;

import com.taxi.service.dict.Constants;
import com.taxi.service.entity.User;
import com.taxi.service.service.ClientService;
import com.taxi.service.service.OrderService;
import com.taxi.service.serviceImpl.ClientServiceImpl;
import com.taxi.service.serviceImpl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminController extends InitController {

    ClientService clientService = (ClientServiceImpl) getClientService();
    OrderService orderService = (OrderServiceImpl) getOrderService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute(Constants.USER) != null
                    && ((User) request.getSession().getAttribute(Constants.USER)).getClientGrant().isAdmin()) {
                request.setAttribute(Constants.USERS, clientService.listSimpleUsers());
                request.setAttribute(Constants.MODERATORS, clientService.listAllModerators());
                request.setAttribute(Constants.NOT_ACTIVE_ORDER_LIST, orderService.notActiveOrderList());
                request.getRequestDispatcher(Constants.ADMIN_PANEL).forward(request, response);
            } else {
                request.getRequestDispatcher(Constants.INDEX).forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
