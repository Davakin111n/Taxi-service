package com.taxi.service.controller;

import com.taxi.service.controller.form.PasswordForm;
import com.taxi.service.controller.form.PrivateInfoForm;
import com.taxi.service.dict.Constants;
import com.taxi.service.entity.User;
import com.taxi.service.serviceImpl.ClientServiceImpl;
import com.taxi.service.utils.PasswordUtil;
import com.taxi.service.validator.PrivateAreaValidator;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends InitController {

    ClientServiceImpl clientService = getClientService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") != null) {
            if (request.getRequestURI().contains("/savePersonData")) {
                savePersonData(request, response);
            } else if (request.getRequestURI().contains("/changePassword")) {
                changePassword(request, response);
            } else if (request.getRequestURI().contains("/madeModerator")) {
                madeModerator(request, response);
            }
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtils.isNotEmpty(request.getParameter("password"))
                || !StringUtils.isNotEmpty(request.getParameter("newPassword"))
                || !StringUtils.isNotEmpty(request.getParameter("secondaryPassword"))) {
            try {
                request.getRequestDispatcher(Constants.ERROR_PATH).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!StringUtils.equals(((User) request.getSession().getAttribute("user")).getPassword()
                , PasswordUtil.encryptPassword(request.getParameter("password")))) {
            try {
                request.getRequestDispatcher(Constants.ERROR_PATH).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            PasswordForm passwordForm = new PasswordForm();
            passwordForm.setPassword(request.getParameter("password"));
            passwordForm.setNewPassword(request.getParameter("newPassword"));
            passwordForm.setSecondaryPassword(request.getParameter("secondaryPassword"));
            User user = (User) request.getSession().getAttribute("user");
            if (PrivateAreaValidator.validateNewPasswordChange(passwordForm, user)) {
                try {
                    clientService.changePassword(user.getId(), passwordForm.getNewPassword());
                    user.setPassword(PasswordUtil.encryptPassword(passwordForm.getNewPassword()));
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect(Constants.PRIVATE_AREA);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    passwordForm = null;
                }
            }
        }
    }

    private void savePersonData(HttpServletRequest request, HttpServletResponse response) {
        PrivateInfoForm privateInfoForm = new PrivateInfoForm();
        privateInfoForm.setName(request.getParameter("clientName"));
        privateInfoForm.setLastName(request.getParameter("clientLastName"));
        privateInfoForm.setAddress(request.getParameter("address"));
        privateInfoForm.setPhone(request.getParameter("phone"));
        privateInfoForm.setSkype(request.getParameter("skype"));
        User user = (User) request.getSession().getAttribute("user");
        if (PrivateAreaValidator.validateSaveData(privateInfoForm)) {
            user.setClientName(privateInfoForm.getName());
            user.setClientLastName(privateInfoForm.getLastName());
            user.setPhone(privateInfoForm.getPhone());
            user.setSkype(privateInfoForm.getSkype());
            clientService.update(user);
            try {
                response.sendRedirect(Constants.PRIVATE_AREA);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                privateInfoForm = null;
            }
        } else {
            try {
                request.getRequestDispatcher(Constants.PRIVATE_AREA_PATH).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void madeModerator(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            clientService.madeModerator(user.getId());
            request.getRequestDispatcher(Constants.PRIVATE_AREA_PATH).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
