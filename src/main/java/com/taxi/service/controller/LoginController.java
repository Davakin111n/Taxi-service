package com.taxi.service.controller;

import com.taxi.service.dict.Constants;
import com.taxi.service.entity.User;
import com.taxi.service.validator.LoginValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends InitController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute(Constants.USER) != null) {
                if (request.getRequestURI().contains("/logout")) {
                    logout(request, response);
                } else if (((User) request.getSession().getAttribute(Constants.USER)).getClientGrant().isModerator()) {
                    request.getRequestDispatcher(Constants.ADMIN_PANEL_PATH).forward(request, response);
                } else {
                    request.getRequestDispatcher(Constants.PRIVATE_AREA_PATH).forward(request, response);
                }
            } else {
                request.getRequestDispatcher(Constants.LOGIN_PATH).forward(request, response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        /**
         * Валидация на пустоту
         */
        if (!LoginValidator.validateLogin(request.getParameter("email"), request.getParameter("password"))) {
            try {
                request.getRequestDispatcher(Constants.ERROR_PATH).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Проверка на существование пользователя в системе
         */
        if (!getClientService().successLogin(request.getParameter("email"), request.getParameter("password"))) {
            try {
                request.getRequestDispatcher(Constants.ERROR_PATH).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            User user = getClientService().getByEmail(request.getParameter("email"));
            request.getSession().setAttribute(Constants.USER, user);
            try {
                request.getRequestDispatcher(Constants.PRIVATE_AREA_PATH).forward(request, response);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().removeAttribute("user");
            request.getSession().invalidate();
            request.logout();
            response.sendRedirect(Constants.INDEX);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
