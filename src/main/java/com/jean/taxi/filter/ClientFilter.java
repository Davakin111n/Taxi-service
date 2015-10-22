package com.jean.taxi.filter;

import java.util.Date;

public class ClientFilter {
    private String clientType;
    private String dateValue;
    private Date currentDate;

    public ClientFilter(String clientType, String dateValue) {
        this.clientType = clientType;
        this.dateValue = dateValue;
        this.currentDate = new Date();
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
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
