package com.taxi.service.controller.form;

import com.taxi.service.entity.OrderAddress;

import java.util.ArrayList;
import java.util.List;

public class OrderForm {

    private String contactName;
    private String phone;
    private String title;
    private String note;
    private String price;
    private String beginAddress;
    private String houseNumber;
    private String porchNumber;
    private String carOption;

    private List<OrderAddress> addressList = new ArrayList<OrderAddress>();

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getCarOption() {
        return carOption;
    }

    public void setCarOption(String carOption) {
        this.carOption = carOption;
    }

    public List<OrderAddress> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<OrderAddress> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "contactName='" + contactName + '\'' +
                ", phone='" + phone + '\'' +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", price='" + price + '\'' +
                ", beginAddress='" + beginAddress + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", porchNumber='" + porchNumber + '\'' +
                ", carOption='" + carOption + '\'' +
                ", addressList=" + addressList +
                '}';
    }
}
