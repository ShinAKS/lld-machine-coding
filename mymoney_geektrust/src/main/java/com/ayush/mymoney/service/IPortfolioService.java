package com.ayush.mymoney.service;

import com.ayush.mymoney.models.Fund;

import java.util.List;

public interface IPortfolioService {

    void allocateFund(List<Double>allocation);

    void initiateSip(List<Double>sipValues);

    void adjustChange(List<Double>changePct, String Month);

    void rebalance(int monthIdx);

    void displayBalance(String Month);

    void displayRebalance();
}
