package com.taxi.service.validator;


import org.apache.commons.lang3.StringUtils;

public class LoginValidator {
    public static boolean validateLogin(String email, String password) {
        return StringUtils.isNotEmpty(email)
                && StringUtils.isNotEmpty(password);
    }
}
