package com.ayush.mymoney.models;

import com.ayush.mymoney.strategy.AllocationStrategy;
import com.ayush.mymoney.vo.Month;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Portfolio {

    private List<MonthlyPortfolioRecord> monthlyRecords;

    private List<Double>allocationSplitPct;

    private List<Double> sipAmount;

    public Portfolio(){
        this.sipAmount = new ArrayList<>();
        this.monthlyRecords = new ArrayList<>();
        this.allocationSplitPct = new ArrayList<>();
    }

}
