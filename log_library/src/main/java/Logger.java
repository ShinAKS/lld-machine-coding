import loggers.DebugLogger;
import loggers.ErrorLogger;
import loggers.InfoLogger;

public class Logger {

    public static Logger LoggerInstance;
    private LogManager logManager;

    private Logger (){
        this.logManager = new LogManager();
        this.logManager.doChaining();
    }

    public static Logger getLoggerInstance(){
        if (LoggerInstance == null){
            synchronized (Logger.class){
                if (LoggerInstance == null){
                    LoggerInstance = new Logger();
                }
            }
        }
        return LoggerInstance;
    }

    public void info(String message){
        InfoLogger logger = this.logManager.getLevelLogger(LogLevel.INFO);
        logger.printLog(message);
    }

    public void error(String message){
        ErrorLogger logger = this.logManager.getLevelLogger(LogLevel.ERROR);
        logger.printLog(message);
    }

    public void debug(String message){
        DebugLogger logger = this.logManager.getLevelLogger(LogLevel.DEBUG);
        logger.printLog(message);
    }
}
