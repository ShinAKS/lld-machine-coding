package com.ayush.mymoney.commands;

import com.ayush.mymoney.models.Command;
import com.ayush.mymoney.service.PortfolioService;

abstract public class CommandExecutor {

    protected PortfolioService portfolioService;

    public CommandExecutor(PortfolioService portfolioService){
        this.portfolioService = portfolioService;
    }

    public void execute(final Command command) {
        if (!isValid(command)) {
            throw new IllegalArgumentException();
        }
        try {
            executeValidCommand(command);
        }catch(IllegalArgumentException e){
            throw e;
        }
    }

    public abstract Boolean isValid(final Command command);

    protected abstract void executeValidCommand(final Command command);

}
