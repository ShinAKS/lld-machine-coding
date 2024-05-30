package loggers;

public abstract class AbstractLogger {

    private int level;

    private AbstractLogger nextLogger;

    public AbstractLogger(int level ){
        this.level = level;

    }

    public void setNextLogger(AbstractLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    public void printLog(String message){
        this.printMessage(message);
        if (this.nextLogger != null){
            this.nextLogger.printLog(message);
        }
    }

    public abstract void printMessage(String message);
}
