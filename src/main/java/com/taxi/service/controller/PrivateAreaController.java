package com.taxi.service.controller;

import com.taxi.service.dict.Constants;
import com.taxi.service.entity.Order;
import com.taxi.service.entity.User;
import com.taxi.service.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PrivateAreaController extends InitController {

    OrderService orderService = getOrderService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute(Constants.USER);
            if (user == null) {
                request.getRequestDispatcher(Constants.LOGIN_PATH).forward(request, response);
            }
            if (user != null && user.getClientGrant().isModerator()) {
                request.getRequestDispatcher(Constants.ADMIN_PANEL_PATH).forward(request, response);
            } else {
                request.setAttribute(Constants.ORDER_LIST_BY_CLIENT, orderService.orderListByClient(((User) request.getSession().getAttribute(Constants.USER)).getId()));
                request.getRequestDispatcher(Constants.PRIVATE_AREA_PATH).forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
