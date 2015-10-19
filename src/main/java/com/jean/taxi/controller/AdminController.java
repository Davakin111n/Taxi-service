package com.jean.taxi.controller;

import com.jean.taxi.dict.Constants;
import com.jean.taxi.entity.User;
import com.jean.taxi.service.ClientService;
import com.jean.taxi.service.OrderService;
import com.jean.taxi.serviceImpl.ClientServiceImpl;
import com.jean.taxi.serviceImpl.OrderServiceImpl;

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
                request.setAttribute(Constants.BAN_LIST, clientService.banList());
                request.setAttribute(Constants.NOT_ACTIVE_ORDER_LIST, orderService.notActiveOrderList());
                request.setAttribute(Constants.ACTIVE_ORDER_LIST, orderService.activeOrderList());
                request.setAttribute(Constants.ACCOMPLISHED_ORDER_LIST, orderService.accomplishedOrderList());
                request.getRequestDispatcher(Constants.ADMIN_PANEL_PATH).forward(request, response);
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
