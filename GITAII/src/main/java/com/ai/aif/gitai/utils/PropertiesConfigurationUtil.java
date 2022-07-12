package com.ai.aif.gitai.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfigurationUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesConfigurationUtil.class);
    private final static Properties prop = new Properties();
    private static final String CONFIGURATION_FILE = "hosts.properties";

    static {
        loadProperty();
    }


    private final static void loadProperty() {
        logger.info("load {} property", CONFIGURATION_FILE);
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIGURATION_FILE)) {
            if (inputStream == null) {
                logger.warn("Can not find {}", CONFIGURATION_FILE);
            } else {
                prop.load(inputStream);
            }
        } catch (IOException e) {
            logger.warn("Can not load property {}", CONFIGURATION_FILE, e);
        }
    }

    public static String getProperty(String propName) {
        return prop.getProperty(propName);
    }

    public static String getProperty(String propName, String defaultValue) {
        String ret = getProperty(propName);
        if (ret == null) {
            return defaultValue;
        } else {
            return ret;
        }
    }

    public static void setIntPropertyValue(String key, int value) {
        prop.setProperty(key, Integer.toString(value));
    }

    public static int getIntPropertyValue(String propName, int defaultValue) {
        String value = getProperty(propName);
        if (value != null) {
            return Integer.parseInt(value);
        }
        return defaultValue;
    }

    public static boolean getBooleanPropertyValue(String propName, boolean defaultValue) {
        String value = getProperty(propName);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    public static void main(String[] args) {
        String property = PropertiesConfigurationUtil.getProperty("gitai.host");
        // System.out.println("property: " + property);
    }
}
