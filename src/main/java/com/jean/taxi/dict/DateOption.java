package com.jean.taxi.dict;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DateOption {
    NO_LIMITS("Без ограничений"),
    BY_TODAY("За сегодня"),
    BY_WEEK("За неделю"),
    BY_MONTH("За месяц");

    private String title;

    public static final List<DateOption> CONDITIONS = new ArrayList<DateOption>(
            Arrays.asList(new DateOption[]{NO_LIMITS, BY_TODAY, BY_WEEK, BY_MONTH}));

    DateOption(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
