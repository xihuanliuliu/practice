package com.ai.aif.gitai.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class YmlPropertyUtil {

    private static final Logger logger = LoggerFactory.getLogger(YmlPropertyUtil.class);

    private static final String CONFIGURATION_FILE = "application.yml";

    private static final Map<String, String> properties = new HashMap<>();

    static {
        loadProperty();
    }

    /**
     * 读取yml文件中的内容
     */
    public static void loadProperty() {
        logger.info("init load property application yml");
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIGURATION_FILE)) {
            if (in == null) {
                logger.warn("can not find application.yml");
            } else {
                Yaml props = new Yaml();
                Object obj = props.loadAs(in, Map.class);
                Map<String, Object> param = (Map<String, Object>) obj;

                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    String key = entry.getKey();
                    Object val = entry.getValue();
                    if (val instanceof Map) {
                        forEachYaml(key, (Map<String, Object>) val);
                    } else {
                        logger.info("load property application yaml. key={}, value={}", key, val.toString());
                        properties.put(key, val.toString());
                    }
                }
            }
        } catch (IOException e) {
            logger.warn("can not load property application.yml", e);
        }
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return properties.get(key);
    }

    /**
     * set相关参数
     * @param key
     * @param value
     */
    public static void setIntPropertyValue(String key, String value) {
        properties.put(key, value);
    }

    /**
     * 获取值为null则读取默认值
     * @param propName
     * @param defaultValue
     * @return
     */
    public static String getStringPropertyValue(String propName, String defaultValue) {
        String value = getProperty(propName);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    /**
     * 获取值为null则读取默认值
     * @param propName
     * @param defaultValue
     * @return
     */
    public static boolean getBooleanPropertyValue(String propName, boolean defaultValue) {
        String value = getProperty(propName);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    /**
     * 遍历yml文件，获取map集合
     * @param keyString
     * @param obj
     * @return
     */
    public static Map<String, String> forEachYaml(String keyString, Map<String, Object> obj) {
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();

            String newKeyString = "";
            if (StringUtils.isNotEmpty(keyString)) {
                newKeyString = keyString + "." + key;
            } else {
                newKeyString = key;
            }
            if (val instanceof Map) {
                forEachYaml(newKeyString, (Map<String, Object>) val);
            } else {
                logger.info("load property application yaml. key={}, value={}", key, val.toString());
                properties.put(newKeyString, val.toString());
            }
        }
        return properties;
    }

    public static void main(String[] args) {
        String property = getProperty("gitai.host");
        String[] split = property.split(",|;");

        for (int i = 0; i < split.length; i++) {
            // System.out.println("host: " + split[i]);
        }
    }
}
