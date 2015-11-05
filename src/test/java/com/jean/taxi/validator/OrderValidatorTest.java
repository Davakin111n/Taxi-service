package com.jean.taxi.validator;

import com.jean.taxi.controller.form.OrderForm;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;

import static org.junit.Assert.*;

public class OrderValidatorTest extends UnitilsJUnit4 {

    OrderForm orderForm = new OrderForm();

    @Test
    public void testValidateNewOrder() {
        String phone = "098765434";
        String beginAddress = "beginAddress";
        String destinationAddress = "destinationAddress";
        assertNotNull(phone);
        assertTrue(OrderValidator.validateNewOrder(phone, beginAddress, destinationAddress));
        phone = "";
        assertFalse(OrderValidator.validateNewOrder(phone, beginAddress, destinationAddress));
    }

    @Test
    public void testValidateOrderEdit() {
        String title = "Order title";
        String phone = "098765434";
        String price = "34567";
        String contactName = "name";
        String beginAddress = "beginAddress";
        orderForm.setPhone(phone);
        orderForm.setPrice(price);
        orderForm.setBeginAddress(beginAddress);
        orderForm.setContactName(contactName);
        orderForm.setTitle(title);
        assertNotNull(orderForm.getPhone());
        assertNotNull(orderForm.getTitle());
        assertNotNull(orderForm.getContactName());
        assertNotNull(orderForm.getBeginAddress());
        assertNotNull(orderForm.getPrice());
        assertTrue(OrderValidator.validateOrderEdit(orderForm));
        title = "";
        orderForm.setTitle(title);
        assertFalse(OrderValidator.validateOrderEdit(orderForm));
    }
}
