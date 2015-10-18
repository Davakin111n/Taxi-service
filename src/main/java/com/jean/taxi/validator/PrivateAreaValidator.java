package com.jean.taxi.validator;

import com.jean.taxi.controller.form.PasswordForm;
import com.jean.taxi.controller.form.PrivateInfoForm;
import com.jean.taxi.entity.User;
import com.jean.taxi.utils.PasswordUtil;
import org.apache.commons.lang3.StringUtils;

public class PrivateAreaValidator {
    public static boolean validateNewPasswordChange(PasswordForm passwordForm, User user) {
        return StringUtils.isNotEmpty(passwordForm.getNewPassword())
                && StringUtils.isNotEmpty(passwordForm.getPassword())
                && StringUtils.isNotEmpty(passwordForm.getSecondaryPassword())
                && StringUtils.equals(PasswordUtil.encryptPassword(passwordForm.getPassword()), user.getPassword())
                && StringUtils.equals(passwordForm.getNewPassword(), passwordForm.getSecondaryPassword());
    }

    public static boolean validateSaveData(PrivateInfoForm privateInfoForm) {
        return StringUtils.isNotEmpty(privateInfoForm.getName());
    }
}

