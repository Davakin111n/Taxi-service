package com.jean.taxi.validator;


import com.jean.taxi.controller.form.OrderForm;
import org.apache.commons.lang3.StringUtils;

public class OrderValidator {

    public static boolean validateNewOrder(String beginAddress, String phone, String destinationAddress) {
        return StringUtils.isNotEmpty(phone)
                && StringUtils.isNotEmpty(beginAddress)
                && StringUtils.isNotEmpty(destinationAddress);
    }

    public static boolean validateOrderEdit(OrderForm orderForm) {
        return StringUtils.isNotEmpty(orderForm.getTitle())
                && StringUtils.isNotEmpty(orderForm.getPhone())
                && StringUtils.isNotEmpty(orderForm.getPrice())
                && StringUtils.isNotEmpty(orderForm.getContactName())
                && StringUtils.isNotEmpty(orderForm.getBeginAddress());
    }
}
