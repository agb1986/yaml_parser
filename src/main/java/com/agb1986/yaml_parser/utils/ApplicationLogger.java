package com.agb1986.yaml_parser.utils;

import com.google.common.base.Joiner;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationLogger {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ApplicationLogger.class);
    private static final Joiner JOINER = Joiner.on("").skipNulls();

    private ApplicationLogger() {
        throw new AssertionError("ApplicationLogger is a utility class cannot be instantiated!");
    }

    private static String getClassAndMethodCall() {
        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
        return JOINER.join(stackTraceElement[3].getClassName(), "::",
                stackTraceElement[3].getMethodName());
    }

    public static void info(String... messages) {
        String infoMessage = JOINER.join(messages);
        infoMessage = JOINER.join(getClassAndMethodCall(), " - ", infoMessage);
        LOGGER.info(infoMessage);
    }

    public static void warn(String... messages) {
        String warnMessage = JOINER.join(messages);
        warnMessage = JOINER.join(getClassAndMethodCall(), " - ", warnMessage);
        LOGGER.warn(warnMessage);
    }

    public static void error(String... messages) {
        String errorMessage = JOINER.join(messages);
        errorMessage = JOINER.join(getClassAndMethodCall(), " - ", errorMessage);
        LOGGER.error(errorMessage);
    }

    public static void error(Exception e, String... messages) {
        String errorMessage = JOINER.join(messages);
        errorMessage =
                JOINER.join(getClassAndMethodCall(), " - ", errorMessage, " - ", e.getMessage());
        LOGGER.error(errorMessage);
    }
}
