public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLoggerInstance();

        logger.info("Info message");

        System.out.println("-----------------------------------");
        logger.error("Error message");
    }
}
