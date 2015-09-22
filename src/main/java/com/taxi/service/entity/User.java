package com.taxi.service.entity;

import java.util.Date;

public class User extends Identifier {

    public User() {

    }

    public User(String email,
                String password,
                String phone,
                String address,
                String clientName,
                String clientLastName,
                String skype, boolean admin,
                Date registrationDate,
                boolean moderator,
                boolean active) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.skype = skype;
        this.admin = admin;
        this.registrationDate = registrationDate;
        this.moderator = moderator;
        this.active = active;
    }

    private String email;
    private String password;
    private String phone;
    private String address;
    private String clientName;
    private String clientLastName;
    private String skype;

    private Date registrationDate = new Date();

    private boolean admin = false;

    private boolean moderator = false;

    private boolean active = false;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isModerator() {
        return moderator;
    }

    public void setModerator(boolean moderator) {
        this.moderator = moderator;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", skype='" + skype + '\'' +
                ", registrationDate=" + registrationDate +
                ", admin=" + admin +
                ", moderator=" + moderator +
                ", active=" + active +
                '}';
    }
}
