package com.taxi.service.controller;

import com.taxi.service.dict.Constants;
import com.taxi.service.entity.User;
import com.taxi.service.service.ClientService;
import com.taxi.service.serviceImpl.ClientServiceImpl;
import com.taxi.service.validator.LoginValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private ClientService clientService = (ClientServiceImpl) getServletConfig().getServletContext().getAttribute("clientService");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getSession().getAttribute(Constants.USER) != null) {
                request.getRequestDispatcher("WEB-INF/pages/privateArea.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        if (!LoginValidator.validateLogin(request.getParameter("email"), request.getParameter("password"))) {
            //тут будет редирект на страницу с ошибкой и с текст ошибки - пустое поле
        }

        if (!clientService.successLogin(request.getParameter("email"), request.getParameter("password"))) {
            //тут будет редирект на страницу с ошибкой и текст ошибки - юзера не существует
        } else {
            User user = clientService.getByEmail(request.getParameter("email"));
            request.getSession().setAttribute(Constants.USER, user);
            try {
                if (user.isModerator()) {
                    request.getRequestDispatcher("WEB-INF/pages/adminPanel.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("WEB-INF/pages/privateArea.jsp").forward(request, response);
                }
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
    }
}
