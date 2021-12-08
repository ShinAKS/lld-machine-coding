package com.ayush.mymoney.models;

import com.ayush.mymoney.vo.FundType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/**
 * Each fund consist of
 * value : Integer value
 * fundtype enum: can be GOLD, DEBT, EQUITY. can be extended
 */
public class Fund {

    private Integer value;

    private FundType fundType;



}
