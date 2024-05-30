package loggers;

import loggers.AbstractLogger;

public class DebugLogger extends AbstractLogger {

    private int level;

    public DebugLogger(int level){
        super(level);
    }


    @Override
    public void printMessage(String message) {
        System.out.println("DEBUG: " + message);
    }
}
