package com.taxi.service.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order extends Identifier {

    private Long clientId;
    private String contactName;

    private String phone;
    private String title;
    private String note;
    private String price;
    private Date createDate = new Date();

    private String beginAddress;
    private String houseNumber;
    private String porchNumber;

    private String carOption;

    private List<OrderAddress> addressList = new ArrayList<OrderAddress>();

    private boolean active = false;
    private boolean onPerfomance = false;
    private boolean accomplished = false;

    public Order() {

    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public boolean isAccomplished() {
        return accomplished;
    }

    public void setAccomplished(boolean accomplished) {
        this.accomplished = accomplished;
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

    public String getBeginAddress() {
        return beginAddress;
    }

    public void setBeginAddress(String beginAddress) {
        this.beginAddress = beginAddress;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPorchNumber() {
        return porchNumber;
    }

    public void setPorchNumber(String porchNumber) {
        this.porchNumber = porchNumber;
    }

    public List<OrderAddress> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<OrderAddress> addressList) {
        this.addressList = addressList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isOnPerfomance() {
        return onPerfomance;
    }

    public void setOnPerfomance(boolean onPerfomance) {
        this.onPerfomance = onPerfomance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCarOption() {
        return carOption;
    }

    public void setCarOption(String carOption) {
        this.carOption = carOption;
    }

    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + clientId +
                ", contactName='" + contactName + '\'' +
                ", phone='" + phone + '\'' +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", price='" + price + '\'' +
                ", createDate=" + createDate +
                ", beginAddress='" + beginAddress + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", porchNumber='" + porchNumber + '\'' +
                ", carOption='" + carOption + '\'' +
                ", addressList=" + addressList +
                ", active=" + active +
                ", onPerfomance=" + onPerfomance +
                ", accomplished=" + accomplished +
                '}';
    }
}
