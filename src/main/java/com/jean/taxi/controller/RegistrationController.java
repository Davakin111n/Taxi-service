package com.jean.taxi.controller;

import com.jean.taxi.dict.Constants;
import com.jean.taxi.entity.User;
import com.jean.taxi.exception.ServiceException;
import com.jean.taxi.service.ClientService;
import com.jean.taxi.serviceImpl.ClientServiceImpl;
import com.jean.taxi.validator.RegistrationValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationController extends InitController {

    ClientService clientService = (ClientServiceImpl) getClientService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute(Constants.USER) != null) {
                request.getRequestDispatcher(Constants.PRIVATE_AREA_PATH).forward(request, response);
            } else {
                request.getRequestDispatcher(Constants.REGISTRATION_PATH).forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (!RegistrationValidator.validateUser(
                request.getParameter("password"),
                request.getParameter("secondaryPassword"),
                request.getParameter("email"))) {
            try {
                request.getRequestDispatcher(Constants.ERROR_PATH).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!RegistrationValidator.validatePassword(
                request.getParameter("password"),
                request.getParameter("secondaryPassword"))) {
            try {
                request.getRequestDispatcher(Constants.ERROR_PATH).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                User user = new User();
                user.setEmail(request.getParameter("email"));
                user.setAddress(request.getParameter("address"));
                user.setPassword(request.getParameter("password"));
                user.setClientName(request.getParameter("clientName"));
                user.setClientLastName(request.getParameter("clientLastName"));
                user.setPhone(request.getParameter("phone"));
                user.setSecondPhone(request.getParameter("secondPhone"));
                user.setThirdPhone(request.getParameter("thirdPhone"));
                user.setSkype(request.getParameter("skype"));
                clientService.addNew(user);
                request.getSession().setAttribute(Constants.USER, user);
                response.sendRedirect(Constants.PRIVATE_AREA);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }
}
