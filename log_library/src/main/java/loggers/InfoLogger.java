package loggers;

import loggers.AbstractLogger;

public class InfoLogger extends AbstractLogger {

    private int level;

    public InfoLogger(int level){
        super(level);
    }

    @Override
    public void printMessage(String message) {
        System.out.println("INFO: " + message);
    }

}
