package com.taxi.service.controller;

import com.taxi.service.controller.form.PasswordForm;
import com.taxi.service.controller.form.PrivateInfoForm;
import com.taxi.service.dict.Constants;
import com.taxi.service.entity.User;
import com.taxi.service.validator.PrivateAreaValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends InitController {

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
        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setNewPassword(request.getParameter("newPassword"));
        passwordForm.setPassword(request.getParameter("password"));
        passwordForm.setSecondaryPassword(request.getParameter("secondaryPassword"));
        User user = (User) request.getSession().getAttribute("user");
        if (PrivateAreaValidator.validateNewPasswordChange(passwordForm, user)) {
            getClientService().changePassword(user.getId(), passwordForm.getNewPassword());
            try {
                request.getRequestDispatcher(Constants.PRIVATE_AREA_PATH).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                passwordForm = null;
            }
        }
    }

    private void savePersonData(HttpServletRequest request, HttpServletResponse response) {
        PrivateInfoForm privateInfoForm = new PrivateInfoForm();
        privateInfoForm.setName(request.getParameter("name"));
        privateInfoForm.setLastName(request.getParameter("lastName"));
        privateInfoForm.setPhone(request.getParameter("phone"));
        privateInfoForm.setSkype(request.getParameter("skype"));
        User user = (User) request.getSession().getAttribute("user");
        if (PrivateAreaValidator.validateSaveData(privateInfoForm)) {
            user.setClientName(privateInfoForm.getName());
            user.setClientLastName(privateInfoForm.getLastName());
            user.setPhone(privateInfoForm.getPhone());
            user.setSkype(privateInfoForm.getSkype());
            getClientService().update(user);
            try {
                request.getRequestDispatcher(Constants.PRIVATE_AREA_PATH).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                privateInfoForm = null;
            }
        }
    }

    private void madeModerator(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        getClientService().madeModerator(user.getId());
        try {
            request.getRequestDispatcher(Constants.PRIVATE_AREA_PATH).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
