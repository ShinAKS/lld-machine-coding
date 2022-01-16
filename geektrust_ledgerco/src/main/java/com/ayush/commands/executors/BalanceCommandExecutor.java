package com.ayush.commands.executors;

import com.ayush.commands.CommandExecutor;
import com.ayush.model.Command;
import com.ayush.service.UserService;

public class BalanceCommandExecutor extends CommandExecutor {

    public BalanceCommandExecutor(UserService userService) {
        super(userService);
    }

    @Override
    public boolean validate(Command command) {
        return command.arguments.length==4;
    }

    @Override
    public void execute(Command command) {
        String[] arguments = command.arguments;
        userService.showBalance(arguments[1],arguments[2],Integer.parseInt(arguments[3]));
    }
}
