package com.ayush.mymoney.mode;

import com.ayush.mymoney.commands.CommandExecutorFactory;
import com.ayush.mymoney.models.Command;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Mode running in which input commands are given from a file.
 */
public class FileMode extends Mode {
    private String fileName;
    private CommandExecutorFactory commandExecutorFactory;

    public FileMode(
            final CommandExecutorFactory commandExecutorFactory,
            final String fileName) {
        super(commandExecutorFactory);
        this.fileName = fileName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void process() throws IOException {
        final File file = new File(fileName);
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return;
        }

        String inputFromFile = reader.readLine();
        while (inputFromFile != null) {
            List<String>inputs = Arrays.asList(inputFromFile.split(" "));
            final Command command = new Command(inputs.get(0),inputs);
            processCommand(command);
            inputFromFile = reader.readLine();
        }
    }
}