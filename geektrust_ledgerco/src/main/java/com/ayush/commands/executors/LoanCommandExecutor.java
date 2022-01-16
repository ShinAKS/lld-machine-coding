package com.ayush.commands.executors;

import com.ayush.model.Command;
import com.ayush.commands.CommandExecutor;
import com.ayush.service.UserService;

public class LoanCommandExecutor extends CommandExecutor {


    public LoanCommandExecutor(UserService userService) {
        super(userService);
    }

    @Override
    public boolean validate(Command command) {
        return command.arguments.length==6;
    }

    @Override
    public void execute(Command command) {
        String[] arguments = command.arguments;
        userService.addLoan(arguments[1],arguments[2]
                                ,Double.parseDouble(arguments[3]),Double.parseDouble(arguments[4])
                                ,Double.parseDouble(arguments[5]));
    }
}
