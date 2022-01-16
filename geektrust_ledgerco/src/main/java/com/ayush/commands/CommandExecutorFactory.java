package com.ayush.commands;

import com.ayush.commands.executors.BalanceCommandExecutor;
import com.ayush.commands.executors.LoanCommandExecutor;
import com.ayush.commands.executors.PaymentCommandExecutor;
import com.ayush.exceptions.InvalidArgumentException;
import com.ayush.exceptions.InvalidCommandException;
import com.ayush.model.Command;
import com.ayush.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {

    private final String LOAN = "LOAN";
    private final String BALANCE = "BALANCE";
    private final String PAYMENT = "PAYMENT";

    private Map<String,CommandExecutor> commandExecutorMap;

    public CommandExecutorFactory(final UserService userService){
        commandExecutorMap = new HashMap<>();
        commandExecutorMap.put(LOAN, new LoanCommandExecutor(userService));
        commandExecutorMap.put(BALANCE, new BalanceCommandExecutor(userService));
        commandExecutorMap.put(PAYMENT, new PaymentCommandExecutor(userService));
    }


    public void executeCommand(Command command){
        if (!commandExecutorMap.containsKey(command.arguments[0])) {
            throw new InvalidCommandException();
        }
        CommandExecutor commandExecutor = commandExecutorMap.get(command.arguments[0]);
        if (commandExecutor.validate(command)==false){
            throw new InvalidArgumentException();
        }
        else{
            commandExecutor.execute(command);
        }
    }

}
