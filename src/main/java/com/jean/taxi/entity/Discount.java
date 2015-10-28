package com.jean.taxi.entity;

public class Discount extends Identifier {

    private Long clientId;
    private String type;
    private String name;
    private String note;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount = (Discount) o;

        if (!clientId.equals(discount.clientId)) return false;
        if (!type.equals(discount.type)) return false;
        if (!name.equals(discount.name)) return false;
        return note.equals(discount.note);

    }

    @Override
    public int hashCode() {
        int result = clientId.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + note.hashCode();
        return result;
    }
}
