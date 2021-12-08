package com.ayush.mymoney.commands.executors;

import com.ayush.mymoney.commands.CommandExecutor;
import com.ayush.mymoney.models.Command;
import com.ayush.mymoney.service.PortfolioService;

public class BalanceCommandExecutor extends CommandExecutor {

    public static final String BALANCE = "BALANCE";

    public BalanceCommandExecutor(PortfolioService portfolioService) {
        super(portfolioService);
    }


    @Override
    public Boolean isValid(Command command) {
        return command.getParams().size()==2;
    }

    @Override
    protected void executeValidCommand(Command command) {

        this.portfolioService.displayBalance(command.getParams().get(1));
    }
}
