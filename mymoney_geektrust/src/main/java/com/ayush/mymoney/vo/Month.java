package com.ayush.mymoney.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Month {

    JANUARY(0,"JANUARY"),
    FEBRUARY(1,"FEBRUARY"),
    MARCH(2,"MARCH"),
    APRIL(3,"APRIL"),
    MAY(4,"MAY"),
    JUNE(5,"JUNE"),
    JULY(6,"JULY"),
    AUGUST(7,"AUGUST"),
    SEPTEMBER(8,"SEPTEMBER"),
    OCTOBER(9,"OCTOBER"),
    NOVEMBER(10,"NOVEMBER"),
    DECEMBER(11,"DECEMBER");

    private final int monthNumber;

    private final String monthName;

    private static final Map<String, Month> MonthLookUp =
            Arrays.stream(Month.values()).collect(Collectors.toMap(Month::getMonthName, Function.identity()));

    public static Month fromName(String monthName) {
        return MonthLookUp.get(monthName);
    }



}