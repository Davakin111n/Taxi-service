package com.taxi.service.validator;

import com.taxi.service.controller.form.PasswordForm;
import com.taxi.service.controller.form.PrivateInfoForm;
import com.taxi.service.entity.User;
import com.taxi.service.utils.PasswordUtil;
import org.apache.commons.lang3.StringUtils;

public class PrivateAreaValidator {
    public static boolean validateNewPasswordChange(PasswordForm passwordForm, User user) {
        return StringUtils.isNotEmpty(passwordForm.getNewPassword())
                && StringUtils.isNotEmpty(passwordForm.getPassword())
                && StringUtils.isNotEmpty(passwordForm.getSecondaryPassword())
                && StringUtils.equals(PasswordUtil.encryptPassword(passwordForm.getPassword()), user.getPassword())
                && StringUtils.equals(passwordForm.getPassword(), passwordForm.getSecondaryPassword());
    }

    public static boolean validateSaveData(PrivateInfoForm privateInfoForm) {
        return StringUtils.isNotEmpty(privateInfoForm.getName());
    }
}

