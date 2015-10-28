package com.jean.taxi.entity;

import java.util.Date;

public class User extends Identifier {

    private ClientGrant clientGrant = new ClientGrant();
    private String email;
    private String address;
    private String password;
    private String phone;
    private String secondPhone;
    private String thirdPhone;
    private String clientName;
    private String clientLastName;
    private String skype;
    private Date registrationDate = new Date();

    public User() {

    }

    public ClientGrant getClientGrant() {
        return clientGrant;
    }

    public void setClientGrant(ClientGrant clientGrant) {
        this.clientGrant = clientGrant;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public String getThirdPhone() {
        return thirdPhone;
    }

    public void setThirdPhone(String thirdPhone) {
        this.thirdPhone = thirdPhone;
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

    @Override
    public String toString() {
        return "User{" +
                "clientGrant=" + clientGrant +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", secondPhone='" + secondPhone + '\'' +
                ", thirdPhone='" + thirdPhone + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", skype='" + skype + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!clientGrant.equals(user.clientGrant)) return false;
        if (!email.equals(user.email)) return false;
        if (!address.equals(user.address)) return false;
        if (!password.equals(user.password)) return false;
        if (!phone.equals(user.phone)) return false;
        if (!secondPhone.equals(user.secondPhone)) return false;
        if (!thirdPhone.equals(user.thirdPhone)) return false;
        if (!clientName.equals(user.clientName)) return false;
        if (!clientLastName.equals(user.clientLastName)) return false;
        if (!skype.equals(user.skype)) return false;
        return registrationDate.equals(user.registrationDate);
    }

    @Override
    public int hashCode() {
        int result = clientGrant.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + secondPhone.hashCode();
        result = 31 * result + thirdPhone.hashCode();
        result = 31 * result + clientName.hashCode();
        result = 31 * result + clientLastName.hashCode();
        result = 31 * result + skype.hashCode();
        result = 31 * result + registrationDate.hashCode();
        return result;
    }
}
