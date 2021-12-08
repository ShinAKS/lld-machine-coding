package com.ayush.mymoney.strategy;

import java.util.List;

public interface AllocationStrategy {

     List<Double> getAllocationDistribution(List<Double>allocatoin);
}
