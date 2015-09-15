package com.taxi.service.controller;

import com.taxi.service.dict.Constants;
import com.taxi.service.entity.User;
import com.taxi.service.utils.PasswordUtil;
import com.taxi.service.validator.RegistrationValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationController extends InitController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute(Constants.USER) != null) {
                response.sendRedirect("/privateArea");
            } else {
                request.getRequestDispatcher("WEB-INF/pages/registration.jsp").forward(request, response);
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
                request.getRequestDispatcher("WEB-INF/pages/error.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!RegistrationValidator.validatePassword(
                request.getParameter("password"),
                request.getParameter("secondaryPassword"))) {
            try {
                request.getRequestDispatcher("WEB-INF/pages/error.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            User user = new User();
            user.setEmail(request.getParameter("email"));
            user.setPassword(PasswordUtil.encryptPassword(request.getParameter("password")));
            user.setClientName(request.getParameter("clientName"));
            user.setClientLastName(request.getParameter("clientLastName"));
            user.setPhone(request.getParameter("phone"));
            user.setSkype(request.getParameter("skype"));
            user = getClientService().addNew(user);
            request.getSession().setAttribute(Constants.USER, user);
            try {
                request.getRequestDispatcher("WEB-INF/pages/privateArea.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
