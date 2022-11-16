package org.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExceptionMessages {
    private static final String exceptionMessagesPropertiesFilePath = "src//main//resources//exceptionMessages.properties";
    private static Properties exceptionMessagesProperties;

    public static String getConfigFileGeneralExceptionMessage(String configName) {
        return getExceptionMessagesProperties().getProperty("config-file-general-exception-message").replace("{0}", configName);
    }

    public static String getNoNodeMatchesTheXPathExpressionExceptionMessage(String xPathExpression) {
        return getExceptionMessagesProperties().getProperty("no-node-matches-the-xPath-expression-exception-message").replace("{0}", xPathExpression);
    }

    public static String getAspectFileGeneralExceptionMessage(String fieldName) {
        return getExceptionMessagesProperties().getProperty("aspect-file-general-exception-message").replace("{0}", fieldName);
    }

    public static String getInvalidFilePathExceptionMessage(String fileName) {
        return getExceptionMessagesProperties().getProperty("invalid-file-path-exception-message").replace("{0}", fileName);
    }

    private static Properties getExceptionMessagesProperties() {
        if(exceptionMessagesProperties == null) {
            initializeExceptionMessageProperties();
        }
        return exceptionMessagesProperties;
    }

    private static void initializeExceptionMessageProperties() {
        try (InputStream input = new FileInputStream(exceptionMessagesPropertiesFilePath)) {
            exceptionMessagesProperties = new Properties();
            exceptionMessagesProperties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
