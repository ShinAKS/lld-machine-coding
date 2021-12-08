package com.ayush.mymoney.commands.executors;

import com.ayush.mymoney.commands.CommandExecutor;
import com.ayush.mymoney.models.Command;
import com.ayush.mymoney.service.PortfolioService;
import com.ayush.mymoney.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class AllocateCommandExecutor extends CommandExecutor {

    public static final String ALLOCATE = "ALLOCATE";

    public AllocateCommandExecutor(PortfolioService portfolioService) {
        super(portfolioService);
    }


    @Override
    public Boolean isValid(Command command) {
        return command.getParams().size()== Constants.MAX_FUNDS + 1;
    }

    @Override
    protected void executeValidCommand(Command command) {

        List<String>values = command.getParams();
        List<Double> allocationValues = new ArrayList<>();
        for (int i = 1 ; i<values.size() ; i++){
            String currAmount = values.get(i);
            Double value = Double.valueOf(currAmount);
            allocationValues.add(value);
        }
        this.portfolioService.allocateFund(allocationValues);
    }
}
