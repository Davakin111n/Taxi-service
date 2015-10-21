package com.jean.taxi.filter;

import java.util.Date;

public class OrderFilter {
    private String orderType;
    private String dateValue;
    private Date currentDate;

    public OrderFilter(String orderType, String dateValue) {
        this.orderType = orderType;
        this.dateValue = dateValue;
        this.currentDate = new Date();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
