package com.ayush.commands;

import com.ayush.model.Command;
import com.ayush.service.UserService;

public abstract class CommandExecutor {

    public UserService userService;

    public CommandExecutor(UserService userService){
        this.userService = userService;
    }

    public abstract boolean validate(Command command);

    public abstract void execute(Command command);
}
