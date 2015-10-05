package com.taxi.service.validator;


import org.apache.commons.lang3.StringUtils;

public class OrderValidator {

    public static boolean validateNewOrder(String beginAddress, String phone, String destinationAddress) {
        return StringUtils.isNotEmpty(phone)
                && StringUtils.isNotEmpty(beginAddress)
                && StringUtils.isNotEmpty(destinationAddress);
    }
}
