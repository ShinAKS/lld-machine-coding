package com.ayush;

import com.ayush.commands.CommandExecutorFactory;
import com.ayush.exceptions.InvalidArgumentException;
import com.ayush.exceptions.InvalidCommandException;
import com.ayush.model.Command;
import com.ayush.service.UserService;

import java.io.*;

public class Driver {

    public static void main(String[] args) throws IOException {

        final UserService service = new UserService();

        CommandExecutorFactory executorFactory = new CommandExecutorFactory(service);

        processFileCommands(args[0],executorFactory);

    }

    static void processFileCommands(String fileName, CommandExecutorFactory executorFactory) throws IOException {
        final File file = new  File(fileName);
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("File is not valid");
            return;
        }

        String inputFromFile = reader.readLine();
        while (inputFromFile!=null && inputFromFile.length()!=0){
//            System.out.println(inputFromFile.length());
            String[] arguments = inputFromFile.split(" ");
            Command command = new Command(arguments);
            try{
                executorFactory.executeCommand(command);
            }catch(InvalidArgumentException | InvalidCommandException e){
                System.out.println("Command or argument is invalid");
            }finally{
                inputFromFile = reader.readLine();
            }
        }
    }
}
