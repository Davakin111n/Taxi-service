package com.taxi.service.controller;

import com.taxi.service.dict.Constants;
import com.taxi.service.entity.Order;
import com.taxi.service.entity.OrderAddress;
import com.taxi.service.entity.User;
import com.taxi.service.service.OrderService;
import com.taxi.service.serviceImpl.OrderServiceImpl;
import com.taxi.service.validator.OrderValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderController extends InitController {

    OrderService orderService = (OrderServiceImpl) getOrderService();

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
        if (!OrderValidator.validateNewOrder(request.getParameter("phone"),
                request.getParameter("beginAddress"),
                request.getParameter("destinationAddress"))) {
            try {
                request.getRequestDispatcher(Constants.PRIVATE_AREA_PATH).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Order order = new Order();
            order.setClientId(((User) request.getSession().getAttribute("user")).getId());
            order.setBeginAddress(request.getParameter("beginAddress"));
            order.setHouseNumber(request.getParameter("houseNumber"));
            order.setPorchNumber(request.getParameter("porchNumber"));
            order.setPhone(request.getParameter("phone"));
            order.setContactName(request.getParameter("contactName"));
            order.setCarOption(request.getParameter("carOption"));
            order.setNote(request.getParameter("note"));
            List list = order.getAddressList();
            OrderAddress orderAddress = new OrderAddress();
            orderAddress.setDestinationAddress(request.getParameter("destinationAddress"));
            orderAddress.setDestinationHouseNumber(request.getParameter("destinationHouseNumber"));
            orderAddress.setDestinationPorchNumber(request.getParameter("destinationPorchNumber"));
            list.add(orderAddress);
            order.setAddressList(list);
            orderService.addNew(order);
            try {
                response.sendRedirect(Constants.PRIVATE_AREA);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) {

    }

    private void editAdvert(HttpServletRequest request, HttpServletResponse response) {

    }
}
