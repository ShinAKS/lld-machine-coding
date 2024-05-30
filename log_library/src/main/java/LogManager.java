import loggers.AbstractLogger;
import loggers.DebugLogger;
import loggers.ErrorLogger;
import loggers.InfoLogger;

import java.util.HashMap;
import java.util.Map;

public class LogManager {

    Map<LogLevel,AbstractLogger> abstractLoggerList = new HashMap<>();

    public LogManager() {
        abstractLoggerList.put(LogLevel.INFO,new InfoLogger(1));
        abstractLoggerList.put(LogLevel.ERROR,new ErrorLogger(2));
        abstractLoggerList.put(LogLevel.DEBUG,new DebugLogger(3));
    }

    public Map<LogLevel,AbstractLogger> doChaining(){
        AbstractLogger infoLogger = abstractLoggerList.get(LogLevel.INFO);
        AbstractLogger errorLogger = abstractLoggerList.get(LogLevel.ERROR);
        AbstractLogger debugLogger = abstractLoggerList.get(LogLevel.DEBUG);

        infoLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);

        return abstractLoggerList;
    }

    public <E extends AbstractLogger> E getLevelLogger(LogLevel logLevel){
        return (E)abstractLoggerList.get(logLevel);
    }


}
