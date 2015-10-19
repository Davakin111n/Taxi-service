package com.jean.taxi.controller;

import com.jean.taxi.controller.form.OrderForm;
import com.jean.taxi.dict.Constants;
import com.jean.taxi.entity.Order;
import com.jean.taxi.entity.OrderAddress;
import com.jean.taxi.entity.User;
import com.jean.taxi.serviceImpl.OrderServiceImpl;
import com.jean.taxi.validator.OrderValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderController extends InitController {

    OrderServiceImpl orderService = (OrderServiceImpl) getOrderService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") != null) {
            if (request.getRequestURI().contains("/order")) {
                viewOrder(request, response);
            } else if (request.getRequestURI().contains("/deleteOrderFromAdmin")) {
                deleteOrderFromAdmin(request, response);
            } else if (request.getRequestURI().contains("/editOrderFromAdmin")) {
                editOrderFromAdmin(request, response);
            } else if (request.getRequestURI().contains("/activateOrder")) {
                activateOrder(request, response);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") != null) {
            if (request.getRequestURI().contains("/createNewOrder")) {
                createOrder(request, response);
            } else if (request.getRequestURI().contains("/updateOrder")) {
                updateOrder(request, response);
            }
        } else if (request.getRequestURI().contains("/createNonClientOrder")) {

        } else {

        }
    }

    private void viewOrder(HttpServletRequest request, HttpServletResponse response) {
        Order order = null;
        if (request.getParameter("id") != null) {
            order = orderService.get(Long.parseLong(request.getParameter("id")));
        }
        if (order != null) {
            request.setAttribute("order", order);
        } else {

        }
        try {
            request.getRequestDispatcher(Constants.ORDER_PATH).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

    private void activateOrder(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("id") != null) {
            orderService.activateOrder(Long.parseLong(request.getParameter("id")));
        }
        try {
            response.sendRedirect(Constants.ADMIN_PANEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteOrderFromAdmin(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("id") != null) {
            orderService.deleteOrder(Long.parseLong(request.getParameter("id")));
        }
        try {
            response.sendRedirect(Constants.ADMIN_PANEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editOrderFromAdmin(HttpServletRequest request, HttpServletResponse response) {
        Order order = null;
        if (request.getParameter("id") != null) {
            order = orderService.get(Long.parseLong(request.getParameter("id")));
        }
        request.setAttribute("order", order);
        try {
            request.getRequestDispatcher(Constants.ORDER_EDIT_PATH).forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) {
        OrderForm orderForm = new OrderForm();
        orderForm.setTitle(request.getParameter("title"));
        orderForm.setPhone(request.getParameter("phone"));
        orderForm.setContactName(request.getParameter("contactName"));
        orderForm.setBeginAddress(request.getParameter("beginAddress"));
        orderForm.setHouseNumber(request.getParameter("houseNumber"));
        orderForm.setPorchNumber(request.getParameter("porchNumber"));
        orderForm.setPrice(request.getParameter("price"));
        OrderAddress orderAddress = new OrderAddress();
        orderAddress.setDestinationAddress(request.getParameter("destinationAddress"));
        orderAddress.setDestinationHouseNumber(request.getParameter("destinationHouseNumber"));
        orderAddress.setDestinationPorchNumber(request.getParameter("destinationPorchNumber"));
        List addressList = orderForm.getAddressList();
        addressList.add(orderAddress);
        orderForm.setAddressList(addressList);
        if (!OrderValidator.validateOrderEdit(orderForm)) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Order order = orderService.get(Long.parseLong(request.getParameter("orderId")));
        System.out.println(orderForm.toString());
        order.setTitle(orderForm.getTitle());
        order.setPhone(orderForm.getPhone());
        order.setContactName(orderForm.getContactName());
        order.setPrice(orderForm.getPrice());
        order.setBeginAddress(orderForm.getBeginAddress());
        order.setHouseNumber(orderForm.getHouseNumber());
        order.setPorchNumber(orderForm.getPorchNumber());
        order.setAddressList(orderForm.getAddressList());
        orderService.update(order);
        request.setAttribute("order", order);
        try {
            request.getRequestDispatcher(Constants.ORDER_PATH).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
