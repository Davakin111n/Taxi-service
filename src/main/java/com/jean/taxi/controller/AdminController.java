package com.jean.taxi.controller;

import com.jean.taxi.dict.ClientType;
import com.jean.taxi.dict.Constants;
import com.jean.taxi.dict.DateOption;
import com.jean.taxi.dict.OrderType;
import com.jean.taxi.entity.User;
import com.jean.taxi.exception.ServiceException;
import com.jean.taxi.filter.ClientFilter;
import com.jean.taxi.filter.OrderFilter;
import com.jean.taxi.serviceImpl.ClientServiceImpl;
import com.jean.taxi.serviceImpl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminController extends InitController {

    ClientServiceImpl clientService = getClientService();
    OrderServiceImpl orderService = getOrderService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println(((User) request.getSession().getAttribute(Constants.USER)).getClientGrant().toString());
            if (request.getSession().getAttribute(Constants.USER) != null
                    && ((User) request.getSession().getAttribute(Constants.USER)).getClientGrant().isAdmin()) {
                if (request.getRequestURI().contains(Constants.ADMIN_PANEL)) {
                    request.setAttribute(Constants.USERS, clientService.listAll());
                    request.setAttribute(Constants.ORDERS, orderService.listAll());
                    request.setAttribute(Constants.ORDER_TYPES, OrderType.values());
                    request.setAttribute(Constants.CLIENT_TYPES, ClientType.values());
                    request.setAttribute(Constants.DATE_OPTIONS, DateOption.values());
                    request.getRequestDispatcher(Constants.ADMIN_PANEL_PATH).forward(request, response);
                }
            } else {
                request.getRequestDispatcher(Constants.INDEX).forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute(Constants.USER) != null
                    && ((User) request.getSession().getAttribute(Constants.USER)).getClientGrant().isAdmin()) {
                if (request.getRequestURI().contains("/orderListByFilter")) {
                    if (request.getParameter("orderType") != null
                            && request.getParameter("orderDateOption") != null) {
                        viewOrdersByFilter(request, response);
                    }
                } else if (request.getRequestURI().contains("/clientListByFilter")) {
                    if (request.getParameter("clientType") != null
                            && request.getParameter("clientDateOption") != null) {
                        viewClientsByFilter(request, response);
                    }
                }
            } else {
                request.getRequestDispatcher(Constants.INDEX).forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private void viewOrdersByFilter(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        OrderFilter orderFilter = new OrderFilter(request.getParameter("orderType"), request.getParameter("orderDateOption"));
        request.setAttribute(Constants.ORDERS, orderService.orderListByFilter(orderFilter));
        request.setAttribute(Constants.USERS, clientService.listAll());
        request.setAttribute(Constants.ORDER_TYPES, OrderType.values());
        request.setAttribute(Constants.CLIENT_TYPES, ClientType.values());
        request.setAttribute(Constants.DATE_OPTIONS, DateOption.values());
        try {
            request.getRequestDispatcher(Constants.ADMIN_PANEL_PATH).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewClientsByFilter(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ClientFilter clientFilter = new ClientFilter(request.getParameter("clientType"), request.getParameter("clientDateOption"));
        request.setAttribute(Constants.USERS, clientService.clientListByFilter(clientFilter));
        request.setAttribute(Constants.ORDERS, orderService.listAll());
        request.setAttribute(Constants.CLIENT_TYPES, ClientType.values());
        request.setAttribute(Constants.ORDER_TYPES, OrderType.values());
        request.setAttribute(Constants.DATE_OPTIONS, DateOption.values());
        try {
            request.getRequestDispatcher(Constants.ADMIN_PANEL_PATH).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
