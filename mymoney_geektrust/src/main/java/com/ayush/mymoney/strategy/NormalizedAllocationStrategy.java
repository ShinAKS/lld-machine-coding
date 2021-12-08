package com.ayush.mymoney.strategy;

import java.util.ArrayList;
import java.util.List;

public class NormalizedAllocationStrategy implements AllocationStrategy{

    @Override
    public List<Double> getAllocationDistribution(List<Double> allocation) {
        Double sumAllocation = 0.0;
        for (Double contribution  : allocation){
            sumAllocation+=contribution;
        }
        List<Double>allocationValues = new ArrayList<>();
        for (Double contribution : allocation){
            allocationValues.add(contribution/sumAllocation);
        }
        return allocationValues;
    }
}
