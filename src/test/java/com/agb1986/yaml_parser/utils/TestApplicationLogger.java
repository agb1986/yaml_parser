package com.agb1986.yaml_parser.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

class TestApplicationLogger {
    
    LogMemoryAppender logMemoryAppender;

    @BeforeEach
    void beforeEach() {
        Logger logger = (Logger) LoggerFactory.getLogger(ApplicationLogger.class);
        logMemoryAppender = new LogMemoryAppender();
        logMemoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.DEBUG);
        logger.addAppender(logMemoryAppender);
        logMemoryAppender.start();
    }


    @Test
    void test_info() {
        String logMessage = "Hello World! - INFO";
        ApplicationLogger.info(logMessage);
        
        assertThat(logMemoryAppender.countEventsForLogger("ApplicationLogger"), equalTo(1));
        assertTrue(logMemoryAppender.contains(logMessage, Level.INFO));
    }
    
    @Test
    void test_warn() {
        String logMessage = "Hello World! - WARN";
        ApplicationLogger.warn(logMessage);
        
        assertThat(logMemoryAppender.countEventsForLogger("ApplicationLogger"), equalTo(1));
        assertTrue(logMemoryAppender.contains(logMessage, Level.WARN));
    }
    
    @Test
    void test_error() {
        String logMessage = "Hello World! - ERROR";
        ApplicationLogger.error(logMessage);
        
        assertThat(logMemoryAppender.countEventsForLogger("ApplicationLogger"), equalTo(1));
        assertTrue(logMemoryAppender.contains(logMessage, Level.ERROR));
    }
    
    @Test
    void test_error_with_Exception() {
        String logMessage = "Hello World! - ERROR";
        String logExceptionMessage = "Exception Message";
        Exception exception = new Exception(logExceptionMessage);
        ApplicationLogger.error(exception, logMessage);
        
        assertThat(logMemoryAppender.countEventsForLogger("ApplicationLogger"), equalTo(1));
        assertTrue(logMemoryAppender.contains(logMessage, Level.ERROR));
        assertTrue(logMemoryAppender.contains(logExceptionMessage, Level.ERROR));
    }

    @AfterEach
    void afterEach() {
        logMemoryAppender.reset();
    }

    private class LogMemoryAppender extends ListAppender<ILoggingEvent> {
        public void reset() {
            this.list.clear();
        }
    
        public boolean contains(String string, Level level) {
            return this.list.stream()
              .anyMatch(event -> event.toString().contains(string)
                && event.getLevel().equals(level));
        }
    
        public int countEventsForLogger(String loggerName) {
            return (int) this.list.stream()
              .filter(event -> event.getLoggerName().contains(loggerName))
              .count();
        }
    }
}
