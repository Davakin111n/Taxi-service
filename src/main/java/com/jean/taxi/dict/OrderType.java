package com.jean.taxi.dict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OrderType {
    ALL("All"),
    NOT_ACTIVE("Not active"),
    ACTIVE("Active"),
    ACCOMPLISHED("Accomplished");

    private String title;

    public static final List<OrderType> CONDITIONS = new ArrayList<OrderType>(
            Arrays.asList(new OrderType[]{ALL, NOT_ACTIVE, ACTIVE, ACCOMPLISHED}));

    public static final List<String> TITLES = new ArrayList<String>(Arrays.asList(new String[]{ALL.getTitle()
            , NOT_ACTIVE.getTitle()
            , ACTIVE.getTitle()
            , ACCOMPLISHED.getTitle()}));

    OrderType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
