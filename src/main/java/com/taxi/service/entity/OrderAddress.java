package com.taxi.service.entity;

import java.util.Date;

public class OrderAddress extends Identifier {

    private Long orderId;
    private String destinationAddress;
    private Date destinationDate;
    private String destinationHouseNumber;
    private String destinationPorchNumber;

    public String getDestinationPorchNumber() {
        return destinationPorchNumber;
    }

    public void setDestinationPorchNumber(String destinationPorchNumber) {
        this.destinationPorchNumber = destinationPorchNumber;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Date getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(Date destinationDate) {
        this.destinationDate = destinationDate;
    }

    public String getDestinationHouseNumber() {
        return destinationHouseNumber;
    }

    public void setDestinationHouseNumber(String destinationHouseNumber) {
        this.destinationHouseNumber = destinationHouseNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
