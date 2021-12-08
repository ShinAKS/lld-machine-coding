package com.ayush.mymoney.commands;

import com.ayush.mymoney.commands.executors.*;
import com.ayush.mymoney.exceptions.InvalidCommandException;
import com.ayush.mymoney.models.Command;
import com.ayush.mymoney.service.PortfolioService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {

    private Map<String, CommandExecutor> commands = new HashMap<>();

    public CommandExecutorFactory(final PortfolioService portfolioService){
        commands.put(AllocateCommandExecutor.ALLOCATE,new AllocateCommandExecutor(portfolioService));
        commands.put(BalanceCommandExecutor.BALANCE,new BalanceCommandExecutor(portfolioService));
        commands.put(ChangeCommandExecutor.CHANGE,new ChangeCommandExecutor(portfolioService));
        commands.put(RebalanceCommandExecutor.REBALANCE,new RebalanceCommandExecutor(portfolioService));
        commands.put(SipCommandExecutor.SIP,new SipCommandExecutor(portfolioService));
    }

    public CommandExecutor getCommandExecutor(final Command command) {
        final CommandExecutor commandExecutor = commands.get(command.getName());
        if (commandExecutor == null) {
            throw new InvalidCommandException("Please enter valid command");
        }
        return commandExecutor;
    }
}
