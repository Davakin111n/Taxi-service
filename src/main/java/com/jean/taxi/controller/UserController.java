package com.jean.taxi.controller;

import com.jean.taxi.controller.form.PasswordForm;
import com.jean.taxi.controller.form.PrivateInfoForm;
import com.jean.taxi.dict.Constants;
import com.jean.taxi.entity.User;
import com.jean.taxi.exception.ServiceException;
import com.jean.taxi.serviceImpl.ClientServiceImpl;
import com.jean.taxi.utils.PasswordUtil;
import com.jean.taxi.validator.PrivateAreaValidator;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends InitController {

    ClientServiceImpl clientService = getClientService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") != null
                && ((User) request.getSession().getAttribute("user")).getClientGrant().isAdmin()) {
            try {
                if (request.getRequestURI().contains("/madeModerator")) {
                    madeModerator(request, response);
                } else if (request.getRequestURI().contains("/setLikeSimpleUser")) {
                    madeSimpleUser(request, response);
                } else if (request.getRequestURI().contains("/banUser")) {
                    banUser(request, response);
                } else if (request.getRequestURI().contains("/undoBanUser")) {
                    undoBanUser(request, response);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") != null) {
            try {
                if (request.getRequestURI().contains("/savePersonData")) {
                    savePersonData(request, response);
                } else if (request.getRequestURI().contains("/changePassword")) {
                    changePassword(request, response);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
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

    private void savePersonData(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        PrivateInfoForm privateInfoForm = new PrivateInfoForm();
        privateInfoForm.setName(request.getParameter("clientName"));
        privateInfoForm.setLastName(request.getParameter("clientLastName"));
        privateInfoForm.setAddress(request.getParameter("address"));
        privateInfoForm.setPhone(request.getParameter("phone"));
        privateInfoForm.setSkype(request.getParameter("skype"));
        User user = (User) request.getSession().getAttribute("user");
        if (PrivateAreaValidator.validateSaveData(privateInfoForm)) {
            user.setClientName(privateInfoForm.getName());
            user.setAddress(privateInfoForm.getAddress());
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
        try {
            clientService.madeModerator(Long.parseLong(request.getParameter("id")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            response.sendRedirect(Constants.ADMIN_PANEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void madeSimpleUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            clientService.madeSimpleUser(Long.parseLong(request.getParameter("id")));
            response.sendRedirect(Constants.ADMIN_PANEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void banUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            clientService.banUser(Long.parseLong(request.getParameter("id")));
            response.sendRedirect(Constants.ADMIN_PANEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void undoBanUser(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            clientService.deleteBanUser(Long.parseLong(request.getParameter("id")));
            response.sendRedirect(Constants.ADMIN_PANEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
