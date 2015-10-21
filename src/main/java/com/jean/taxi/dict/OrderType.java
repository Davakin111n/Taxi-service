package com.jean.taxi.dict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OrderType {
    ALL("Все"),
    NOT_ACTIVE("Выбраковка"),
    ACTIVE("Некондиция"),
    ACCOMPLISHED("Новый (-ое)");

    private String title;

    public static final List<OrderType> CONDITIONS = new ArrayList<OrderType>(
            Arrays.asList(new OrderType[]{ALL, NOT_ACTIVE, ACTIVE, ACCOMPLISHED}));

    OrderType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
