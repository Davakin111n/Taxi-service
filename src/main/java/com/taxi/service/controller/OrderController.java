package com.taxi.service.controller;

import com.taxi.service.dict.Constants;
import com.taxi.service.entity.Order;
import com.taxi.service.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderController extends InitController {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") != null) {
            if (request.getRequestURI().contains("/createNewOrder")) {
                activateOrder(request, response);
            } else if (request.getRequestURI().contains("/deleteOrder")) {
                deleteOrder(request, response);
            }
        }
    }

    private void activateOrder(HttpServletRequest request, HttpServletResponse response) {

    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) {
        Long orderId = new Long(100);//написано исключительно для тестового режима, будет удалено! Very soon!
        if (request.getSession().getAttribute(Constants.USER) != null) {
            Order order = (Order) getOrderService().get(orderId);
            if (order != null && ((User) request.getSession().getAttribute(Constants.USER)).getId() == order.getIdClient()) {

            }
        }
    }
}
