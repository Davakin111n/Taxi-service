package com.jean.taxi.validator;

import org.apache.commons.lang3.StringUtils;

public class RegistrationValidator {
    public static boolean validateUser(String password, String secondaryPassword, String email) {
        return StringUtils.isNotEmpty(password)
                && StringUtils.isNotEmpty(secondaryPassword)
                && StringUtils.isNotEmpty(email)
                && StringUtils.equals(password, secondaryPassword);
    }

    public static boolean validatePassword(String password, String secondaryPassword) {
        return StringUtils.isNotEmpty(password)
                && StringUtils.isNoneEmpty(secondaryPassword)
                && StringUtils.equals(password, secondaryPassword);
    }
}
