package com.jean.taxi.dict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DateOption {
    NO_LIMITS("No limits"),
    BY_TODAY("By today"),
    BY_WEEK("By week"),
    BY_MONTH("By month");

    private String title;

    public static final List<DateOption> DATE_LIMITS = new ArrayList<DateOption>(
            Arrays.asList(new DateOption[]{NO_LIMITS, BY_TODAY, BY_WEEK, BY_MONTH}));

    public static final List<String> TITLES = new ArrayList<String>(
            Arrays.asList(new String[]{NO_LIMITS.getTitle(), BY_TODAY.getTitle(), BY_WEEK.getTitle(), BY_MONTH.getTitle()}));

    DateOption(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
