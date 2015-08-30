package com.taxi.service.entity;

import java.util.Date;

public class Order extends Identifier {

    private String title;
    private String note;
    private String price;
    private Long idClient;
    private String location;
    private Date createDate = new Date();

    private boolean active = false;

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

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
                ", idClient=" + idClient +
                ", location='" + location + '\'' +
                ", createDate=" + createDate +
                ", active=" + active +
                '}';
    }
}
