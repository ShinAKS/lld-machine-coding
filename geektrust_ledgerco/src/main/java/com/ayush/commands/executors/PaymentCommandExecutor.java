package com.ayush.commands.executors;

import com.ayush.model.Command;
import com.ayush.commands.CommandExecutor;
import com.ayush.service.UserService;

public class PaymentCommandExecutor extends CommandExecutor {

    public PaymentCommandExecutor(UserService userService) {
        super(userService);
    }

    @Override
    public boolean validate(Command command) {
        return command.arguments.length==5;
    }

    @Override
    public void execute(Command command) {
        String[] arguments = command.arguments;
        userService.addPayment(arguments[1],arguments[2],Double.parseDouble(arguments[3]),Integer.parseInt(arguments[4]));
    }
}
