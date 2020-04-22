package app.n_chain;

import app.n_chain.logger.AbstractLogger;
import app.n_chain.logger.ConsoleLogger;
import app.n_chain.logger.ErrorLogger;
import app.n_chain.logger.FileLogger;

/**
 * 责任链模式（Chain of Responsibility Pattern）为请求创建了一个接收者对象的链。
 * 这种模式给予请求的类型，对请求的发送者和接收者进行解耦。这种类型的设计模式属于行为型模式。
 * 在这种模式中，通常每个接收者都包含对另一个接收者的引用。
 * 如果一个对象不能处理该请求，那么它会把相同的请求传给下一个接收者，依此类推。
 */
public class Test {

    private static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.INFO);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.DEBUG);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();
        loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");
        System.out.println("---------------------");
        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");
        System.out.println("---------------------");
        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }
}