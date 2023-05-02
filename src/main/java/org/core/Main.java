package org.core;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.utils.ConfigProperties;

public class Main {
    private static Logger logger;
    private final static String configFilePath = "src//main//resources//config.properties";

    public static void main(String[] args) {
        BasicConfigurator.configure();
        logger = Logger.getLogger(Main.class);

        try {
            ConfigProperties.configure(configFilePath);
            var encapsulator = new Encapsulator();
            encapsulator.encapsulate();
        }
        catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
