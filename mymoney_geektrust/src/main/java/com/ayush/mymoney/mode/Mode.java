package com.ayush.mymoney.mode;

import com.ayush.mymoney.commands.CommandExecutorFactory;
import com.ayush.mymoney.exceptions.InvalidCommandException;
import com.ayush.mymoney.models.Command;
import com.ayush.mymoney.commands.CommandExecutor;

import java.io.IOException;

/**
 * Interface for mode of the program in which it can be run.
 */
public abstract class Mode {

    private CommandExecutorFactory commandExecutorFactory;

    public Mode(
            final CommandExecutorFactory commandExecutorFactory) {
        this.commandExecutorFactory = commandExecutorFactory;
    }

    /**
     * Helper method to process a command. It basically uses {@link CommandExecutor} to run the given
     * command.
     *
     * @param command Command to be processed.
     */
    protected void processCommand(final Command command) {

        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if (commandExecutor.isValid(command)) {
            commandExecutor.execute(command);
        } else {
            throw new InvalidCommandException("Command is invalid");
        }
    }

    /**
     * Abstract method to process the mode. Each mode will process in its own way.
     *
     * @throws IOException
     */
    public abstract void process() throws IOException;
}