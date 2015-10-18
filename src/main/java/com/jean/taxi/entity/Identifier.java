package com.jean.taxi.entity;


public class Identifier {

    private long id = -1;

    /**
     * check new entity or not
     */
    public boolean isNew() {
        return id < 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}