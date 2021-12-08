package com.ayush.mymoney.vo;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum FundType {

    EQUITY(0),
    GOLD(1),
    DEBT(2);

    @Getter private int index;

    FundType(int index){
        this.index = index;
    }

    private static final Map<Integer, FundType> LOOKUP =
            Arrays.stream(FundType.values()).collect(Collectors.toMap(FundType::getIndex, Function.identity()));

    public static FundType fromIdx(Integer index) {
        return LOOKUP.get(index);
    }



}