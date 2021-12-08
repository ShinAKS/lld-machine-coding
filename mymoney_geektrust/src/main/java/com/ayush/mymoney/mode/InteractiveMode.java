package com.ayush.mymoney.mode;

import com.ayush.mymoney.commands.CommandExecutorFactory;
import com.ayush.mymoney.models.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Mode running in which input commands are given from an interactive shell.
 */
public class InteractiveMode extends Mode {

    public InteractiveMode(
            final CommandExecutorFactory commandExecutorFactory) {
        super(commandExecutorFactory);
    }

    @Override
    public void process() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                final List<String> inputs = Arrays.asList(reader.readLine().split(" "));
                final Command command = new Command(inputs.get(0), inputs);
                processCommand(command);
            }catch(NullPointerException e){
                break;
            }
        }
    }
}