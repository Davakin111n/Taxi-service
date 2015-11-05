package com.jean.taxi.validator;

import org.junit.Test;
import org.unitils.UnitilsJUnit4;

import static org.junit.Assert.*;

public class LoginValidatorTest extends UnitilsJUnit4 {

    @Test
    public void testValidateLogin() {
        String email = "email";
        String password = "somePassword";
        assertNotNull(email);
        assertNotNull(password);
        assertTrue(LoginValidator.validateLogin(email, password));
        email = "";
        assertFalse(LoginValidator.validateLogin(email, password));
    }
}
