package com.jean.taxi.validator;

import org.apache.commons.lang3.StringUtils;

public class RegistrationValidator {
    public static boolean validateUser(String password, String secondaryPassword, String email) {
        return StringUtils.isNotEmpty(password)
                && StringUtils.isNotEmpty(secondaryPassword)
                && StringUtils.isNotEmpty(email);
    }

    public static boolean validatePassword(String password, String secondaryPassword) {
        return StringUtils.equals(password, secondaryPassword);
    }
}
