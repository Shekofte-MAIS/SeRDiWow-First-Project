package org.utils;

import org.exceptions.ConfigFileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigProperties {
    private static Properties configProperties;
    public static String bpmnFilePath;
    public static String aspectFilePath;
    public static String wovenBpmnFilePath;
    public static HookType hookType;

    /**
     * necessary to be called at first
     * TODO: see whether there is a better way for implementing this, as other static methods are dependent on the properties which is filled in the method!
     */
    public static void configure(String propertiesPath) throws IOException, ConfigFileException {
        InputStream input = new FileInputStream(propertiesPath);
        configProperties = new Properties();
        configProperties.load(input);
        initializeProperties();
    }

    private static void initializeProperties() throws ConfigFileException {
        bpmnFilePath = getFilePath("bpmn-file-path");
        aspectFilePath = getFilePath("aspect-file-path");
        wovenBpmnFilePath = getWovenBpmnFilePath();
        hookType = getHookType();
    }

    private static String getFilePath(String filePathPropertyKey) throws ConfigFileException {
        var filePath = configProperties.getProperty(filePathPropertyKey);
        Path path = Paths.get(filePath);
        if (!Files.isRegularFile(path)) {
            throw new ConfigFileException(ExceptionMessages.getInvalidFilePathExceptionMessage(filePath));
        }
        return filePath;
    }

    private static String getWovenBpmnFilePath() throws ConfigFileException {
        var filePath = configProperties.getProperty("woven-bpmn-file-path");
        String bpmnFileDirectory = new File(filePath).getParent();
        Path path = Paths.get(bpmnFileDirectory);
        if(!Files.isDirectory(path)) {
            throw new ConfigFileException(ExceptionMessages.getInvalidFilePathExceptionMessage(filePath));
        }
        return filePath;
    }

    private static HookType getHookType() throws ConfigFileException {
        String hookType = configProperties.getProperty("hook-type");
        try {
            return HookType.valueOf(hookType);
        }
        catch (IllegalArgumentException ex) {
            throw new ConfigFileException(ExceptionMessages.getConfigFileGeneralExceptionMessage("hook type"));
        }
    }
}
