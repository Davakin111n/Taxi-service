package com.jean.taxi.entity;

import java.util.Date;

public class Review extends Identifier {

    private String clientName;
    private String note;
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        return "Review{" +
                "clientName='" + clientName + '\'' +
                ", note='" + note + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (!clientName.equals(review.clientName)) return false;
        if (!note.equals(review.note)) return false;
        return createDate.equals(review.createDate);

    }

    @Override
    public int hashCode() {
        int result = clientName.hashCode();
        result = 31 * result + note.hashCode();
        result = 31 * result + createDate.hashCode();
        return result;
    }
}
