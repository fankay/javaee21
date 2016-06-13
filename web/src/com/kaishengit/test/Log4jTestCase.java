package com.kaishengit.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTestCase {

    @Test
    public void testLog() {

        String name = "Alex";
        String bookName = "《重构》";

        Logger logger = LoggerFactory.getLogger(Log4jTestCase.class); //Logger.getLogger(Log4jTestCase.class);
        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("{}借阅了{}",name,bookName);
        logger.warn("warn message");
        logger.error("error message");
        //logger.fatal("fatal message");
    }

}
