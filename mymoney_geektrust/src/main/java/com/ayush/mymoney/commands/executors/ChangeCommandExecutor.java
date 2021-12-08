package com.ayush.mymoney.commands.executors;

import com.ayush.mymoney.commands.CommandExecutor;
import com.ayush.mymoney.models.Command;
import com.ayush.mymoney.service.PortfolioService;
import com.ayush.mymoney.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class ChangeCommandExecutor extends CommandExecutor {

    public static final String CHANGE = "CHANGE";

    public ChangeCommandExecutor(PortfolioService portfolioService) {
        super(portfolioService);
    }

    @Override
    public Boolean isValid(Command command) {
        return command.getParams().size()== Constants.MAX_FUNDS +2;
    }

    @Override
    protected void executeValidCommand(final Command command) {

        List<Double>changeRates = new ArrayList<>();
        int numArgs = command.getParams().size();
        for(int i = 1 ;i<numArgs-1 ; i++){
            String changePct = command.getParams().get(i);
            Double changeValue = Double.parseDouble(changePct.substring(0,changePct.length()-1));
            changeRates.add(changeValue);
        }

        this.portfolioService.adjustChange(changeRates,command.getParams().get(numArgs-1));

    }
}
