package com.taxi.service.controller;

import com.taxi.service.daoImpl.ClientDaoImpl;
import com.taxi.service.daoImpl.OrderDaoImpl;
import com.taxi.service.serviceImpl.ClientServiceImpl;
import com.taxi.service.serviceImpl.OrderServiceImpl;
import com.taxi.service.utils.DataBaseUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

public class InitController extends HttpServlet {

    private DataSource dataSource = DataBaseUtil.getConnectionPoolInstance();
    private ServletContext servletContext = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = this.getServletConfig().getServletContext();
        servletContext.setAttribute("clientDao", new ClientDaoImpl(dataSource));
        servletContext.setAttribute("orderDao", new OrderDaoImpl(dataSource));
        servletContext.setAttribute("clientService", new ClientServiceImpl());
        servletContext.setAttribute("orderService", new OrderServiceImpl());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("WEB-INF/pages/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            super.doPost(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
