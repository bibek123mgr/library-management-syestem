package com.example.library.management.system.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {

    private AppLogger(){}

    public static final Logger logger= LoggerFactory.getLogger("AppLogger");

    public static void info(String message){
        logger.info(message);
    }
    public static void warn(String message){
        logger.warn(message);
    }
    public static void debug(String message){
        logger.debug(message);
    }
    public static void error(String message,Throwable t){
        logger.error(message,t);
    }

}
