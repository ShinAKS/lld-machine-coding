package loggers;

import loggers.AbstractLogger;

public class ErrorLogger extends AbstractLogger {

    private int level;

    public ErrorLogger(int level){
        super(level);
    }

    @Override
    public void printMessage(String message) {
        System.out.println("ERROR: " + message);

    }
}
