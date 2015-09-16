package com.taxi.service.controller;

import com.taxi.service.dict.Constants;
import com.taxi.service.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrivateAreaController extends InitController {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute(Constants.USER);
            if (user == null) {
                request.getRequestDispatcher(Constants.LOGIN).forward(request, response);
            }

            if (user != null) {

            } else {
                request.setAttribute(Constants.ORDER_LIST_BY_CLIENT, getOrderService().orderListByClient(((User) request.getSession().getAttribute(Constants.USER)).getId()));
                request.setAttribute(Constants.NOT_ACTIVE_ORDER_LIST_BY_CLIENT, getOrderService().notActiveOrderListByClient(((User) request.getSession().getAttribute(Constants.USER)).getId()));
                request.getRequestDispatcher(Constants.ADMIN_PANEL).forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
