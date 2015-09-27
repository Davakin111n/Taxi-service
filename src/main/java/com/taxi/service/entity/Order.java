package com.taxi.service.entity;

import java.util.Date;

public class Order extends Identifier {

    private String title;
    private String note;
    private String price;
    private Date createDate = new Date();

    private String clientAddress;
    private String clientHouseNumber;
    private String porchNumber;

    private String destinationAddress;
    private String destinationHouseNumber;
    private String destinationPorchNumber;
    private String destinationDate;

    private boolean active = false;
    private boolean onPerfomance = false;

    public boolean isOnPerfomance() {
        return onPerfomance;
    }

    public void setOnPerfomance(boolean onPerfomance) {
        this.onPerfomance = onPerfomance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientHouseNumber() {
        return clientHouseNumber;
    }

    public void setClientHouseNumber(String clientHouseNumber) {
        this.clientHouseNumber = clientHouseNumber;
    }

    public String getPorchNumber() {
        return porchNumber;
    }

    public void setPorchNumber(String porchNumber) {
        this.porchNumber = porchNumber;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getDestinationHouseNumber() {
        return destinationHouseNumber;
    }

    public void setDestinationHouseNumber(String destinationHouseNumber) {
        this.destinationHouseNumber = destinationHouseNumber;
    }

    public String getDestinationPorchNumber() {
        return destinationPorchNumber;
    }

    public void setDestinationPorchNumber(String destinationPorchNumber) {
        this.destinationPorchNumber = destinationPorchNumber;
    }

    public String getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(String destinationDate) {
        this.destinationDate = destinationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Order{" +
                "title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", price='" + price + '\'' +
                ", createDate=" + createDate +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientHouseNumber='" + clientHouseNumber + '\'' +
                ", porchNumber='" + porchNumber + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", destinationHouseNumber='" + destinationHouseNumber + '\'' +
                ", destinationPorchNumber='" + destinationPorchNumber + '\'' +
                ", destinationDate='" + destinationDate + '\'' +
                ", active=" + active +
                ", onPerfomance=" + onPerfomance +
                '}';
    }
}
