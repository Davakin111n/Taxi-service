package com.taxi.service.entity;

import java.util.Date;

public class Reviews extends Identifier {
    private String clientId;
    private String clientName;
    private String note;
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "clientId='" + clientId + '\'' +
                ", clientName='" + clientName + '\'' +
                ", note='" + note + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
