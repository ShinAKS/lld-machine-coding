package com.ayush.mymoney.service;

import com.ayush.mymoney.exceptions.PortfolioExistsException;
import com.ayush.mymoney.models.Fund;
import com.ayush.mymoney.models.MonthlyPortfolioRecord;
import com.ayush.mymoney.models.Portfolio;
import com.ayush.mymoney.repository.MonthlyPortfolioRepository;
import com.ayush.mymoney.strategy.AllocationStrategy;
import com.ayush.mymoney.utils.Constants;
import com.ayush.mymoney.vo.FundType;
import com.ayush.mymoney.vo.Month;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PortfolioService implements IPortfolioService{

    private Portfolio portfolio;
    private AllocationStrategy allocationStrategy;
    private MonthlyPortfolioRepository repository;

    public PortfolioService(final Portfolio portfolio,final AllocationStrategy strategy){
        //checking if portfolio is already instantiated
        //in that case throw error
        if (this.portfolio!=null){
            throw new PortfolioExistsException("Portfolio already exists");
        }
        this.portfolio = portfolio;
        this.allocationStrategy = strategy;
        this.repository = MonthlyPortfolioRepository.getInstance();
    }

    /**
     * allocateFund will set the initialAllocation array in the portfolio
     * It also stores the value marked against january in the portfolio
     * Also sets the initial allocation percentages
     * @param allocations
     */
    @Override
    public void allocateFund(List<Double> allocations) {
        List<Fund>fundValues = IntStream.range(0,allocations.size())
                                        .mapToObj(idx -> new Fund(allocations.get(idx).intValue(), FundType.fromIdx(idx)))
                                        .collect(Collectors.toList());
        this.portfolio.setAllocationSplitPct(this.allocationStrategy.getAllocationDistribution(allocations));
        this.repository.addMonthlyPortfolioRecord(0,fundValues);
    }

    public void addSip(List<Double> sipValues,int monthIdx) {
        if (monthIdx>0) {
            List<Fund> lastMonthFunds = repository.getFundsByIndex(monthIdx-1);
            List<Fund> currentMonthFunds = new ArrayList<>();
            for (int i = 0; i < Constants.MAX_FUNDS; i++) {
                currentMonthFunds.add(new Fund(lastMonthFunds.get(i).getValue() + sipValues.get(i).intValue(), lastMonthFunds.get(i).getFundType()));
            }
            this.repository.addMonthlyPortfolioRecord(monthIdx, currentMonthFunds);
        }
    }

    /**
     * adjust monthly change of balance based on changePercent and given month
     * @param changePct
     * @param month
     */
    @Override
    public void adjustChange(final List<Double> changePct, final String month) {
        final int monthIdx = Month.fromName(month).getMonthNumber();
        addSip(this.portfolio.getSipAmount(),monthIdx);
        List<Fund>currentMonthFunds = repository.getFundsByIndex(monthIdx);
        for (int i = 0; i<Constants.MAX_FUNDS ; i++){
            double currentValue = currentMonthFunds.get(i).getValue();
            double delta = currentValue * changePct.get(i)/100.0;
            currentValue+=delta;
            currentMonthFunds.get(i).setValue((int)currentValue);
        }
        if (monthIdx==5 || monthIdx==11)rebalance(monthIdx);
    }

    /**
     * rebalances the value set on initial allocation percentage.
     * @param monthIdx
     */
    @Override
    public void rebalance(final int monthIdx) {
        List<Fund>currentMonthFunds = repository.getFundsByIndex(monthIdx);
        double totalFund = currentMonthFunds.stream().mapToDouble(Fund::getValue).sum();
        for (int i = 0 ; i<Constants.MAX_FUNDS ; i++){
            double fundAllocationPct = this.portfolio.getAllocationSplitPct().get(i);
            Double currValue = totalFund*fundAllocationPct;
            currentMonthFunds.get(i).setValue(currValue.intValue());
        }
    }

    /**
     * displays adjusted/rebalanced(if possible) balance for given month.
     * @param month
     */
    @Override
    public void displayBalance(String month) {
        int monthIdx = Month.fromName(month).getMonthNumber();
        List<Fund>monthFunds = repository.getFundsByIndex(monthIdx);
        for (int i = 0 ; i<Constants.MAX_FUNDS ; i++){
            System.out.print(monthFunds.get(i).getValue());
            if (i!=Constants.MAX_FUNDS-1)System.out.print(" ");
        }
        System.out.print("\n");
    }

    /**
     * initiates the sipAmounts
     * @param sipAmounts
     */
    @Override
    public void initiateSip(List<Double>sipAmounts){
        this.portfolio.setSipAmount(sipAmounts);
    }


    @Override
    public void displayRebalance(){
        if (this.repository.getSize()<6){
            System.out.print("CANNOT_REBALANCE");
            System.out.print("\n");
        }
        else if (this.repository.getSize()<12){
            displayBalance(Month.JUNE.getMonthName());
        }
        else displayBalance(Month.DECEMBER.getMonthName());
    }
}
