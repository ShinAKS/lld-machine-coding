package com.ayush.mymoney.commands.executors;

import com.ayush.mymoney.commands.CommandExecutor;
import com.ayush.mymoney.models.Command;
import com.ayush.mymoney.service.PortfolioService;

public class RebalanceCommandExecutor extends CommandExecutor {

    public static final String REBALANCE = "REBALANCE";

    public RebalanceCommandExecutor(PortfolioService portfolioService) {
        super(portfolioService);
    }

    @Override
    public Boolean isValid(Command command) {
        return command.getParams().size()==1;
    }

    @Override
    protected void executeValidCommand(Command command) {
        this.portfolioService.displayRebalance();
    }
}
