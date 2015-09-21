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
                request.getRequestDispatcher(Constants.PRIVATE_AREA).forward(request, response);
            } else {
                request.getRequestDispatcher(Constants.LOGIN).forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
                request.getRequestDispatcher(Constants.ERROR).forward(request, response);
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
                request.getRequestDispatcher(Constants.ERROR).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            User user = getClientService().getByEmail(request.getParameter("email"));
            request.getSession().setAttribute(Constants.USER, user);
            try {
                request.getRequestDispatcher(Constants.PRIVATE_AREA).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        try {
            request.getRequestDispatcher(Constants.INDEX).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
