package com.taxi.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderController extends InitController {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") != null) {
            if (request.getRequestURI().contains("/createNewOrder")) {
                createOrder(request, response);
            } else if (request.getRequestURI().contains("/deleteOrder")) {
                deleteOrder(request, response);
            } else if (request.getRequestURI().contains("/editOrder")) {
                editAdvert(request, response);
            }
        } else if (request.getRequestURI().contains("/createNonClientOrder")) {

        } else {

        }
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response) {

    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) {

    }

    private void editAdvert(HttpServletRequest request, HttpServletResponse response) {

    }
}
