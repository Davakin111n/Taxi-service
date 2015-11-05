package com.jean.taxi.validator;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;

import static org.junit.Assert.*;

public class RegistrationValidatorTest extends UnitilsJUnit4 {
    @Test
    public void testValidateUser() {
        String password = "password";
        String secondaryPassword = "password";
        String email = "email";
        assertNotNull(password);
        assertNotNull(secondaryPassword);
        assertNotNull(email);
        assertTrue(RegistrationValidator.validateUser(password, secondaryPassword, email));
        password = "someChangedPassword";
        assertFalse(RegistrationValidator.validateUser(password, secondaryPassword, email));
    }

    @Test
    public void testValidatePassword() {
        String password = "password";
        String secondaryPassword = "password";
        assertNotNull(password);
        assertNotNull(secondaryPassword);
        assertTrue(RegistrationValidator.validatePassword(password, secondaryPassword));
        password = "";
        assertFalse(RegistrationValidator.validatePassword(password, secondaryPassword));
    }
}
