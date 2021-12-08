package com.ayush.mymoney.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
/**
 * monthIdx : January is mapped to 0, Feb to 1 etc.
 * funds : list of funds for the given month.
 */
public class MonthlyPortfolioRecord {

    private int monthIdx;

    private List<Fund> funds;


}
