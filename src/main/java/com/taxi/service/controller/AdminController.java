package com.taxi.service.controller;

import com.taxi.service.dict.Constants;
import com.taxi.service.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminController extends InitController {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute(Constants.USER) != null
                    && ((User) request.getSession().getAttribute(Constants.USER)).getClientGrant().isAdmin()) {
                request.setAttribute(Constants.USERS, getClientService().listSimpleUsers());
                request.setAttribute(Constants.MODERATORS, getClientService().listAllModerators());
                request.setAttribute(Constants.NOT_ACTIVE_ORDER_LIST, getOrderService().notActiveOrderList());
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
