package com.jean.taxi.dict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ClientType {
    ALL("All"),
    MODERATORS("Moderators"),
    ACTIVE("Active"),
    BANNED("Banned");

    private String title;

    public static final List<ClientType> CONDITIONS = new ArrayList<ClientType>(
            Arrays.asList(new ClientType[]{ALL, MODERATORS, ACTIVE, BANNED}));

    ClientType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
