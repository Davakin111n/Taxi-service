package com.jean.taxi.validator;

import com.jean.taxi.controller.form.PasswordForm;
import com.jean.taxi.entity.User;
import com.jean.taxi.utils.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;

import static org.junit.Assert.*;


public class PrivateAreaValidatorTest extends UnitilsJUnit4 {
    PasswordForm passwordForm = new PasswordForm();
    User user = new User();

    @Test
    public void testValidateNewPasswordChange() {
        user.setPassword(PasswordUtil.encryptPassword("password"));
        passwordForm.setPassword("password");
        passwordForm.setNewPassword("newPassword");
        passwordForm.setSecondaryPassword("newPassword");
        assertNotNull(user.getPassword());
        assertNotNull(passwordForm.getNewPassword());
        assertNotNull(passwordForm.getPassword());
        assertNotNull(passwordForm.getSecondaryPassword());
        assertTrue(PrivateAreaValidator.validateNewPasswordChange(passwordForm, user));
        assertEquals(PasswordUtil.encryptPassword(passwordForm.getPassword()), user.getPassword());
        assertEquals(passwordForm.getNewPassword(), passwordForm.getSecondaryPassword());
        user.setPassword("somePassword");
        assertFalse(PrivateAreaValidator.validateNewPasswordChange(passwordForm, user));
    }

    @Test
    public void testValidateSaveData() {
        String newName = "newName";
        assertNotNull(newName);
        assertFalse(StringUtils.isBlank(newName));
        newName = "";
        assertTrue(StringUtils.isBlank(newName));
    }
}
