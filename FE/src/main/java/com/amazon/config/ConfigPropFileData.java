package com.amazon.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigPropFileData {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPropFileData.class);

    private static ConfigPropFileData configdata;

    private String browser;
    private String url;

    private ConfigPropFileData() {

        this.url = readPropertiesFile().getProperty("url");
        this.browser = readPropertiesFile().getProperty("browser");
    }

    public static ConfigPropFileData getInstance() {
        if (null == configdata) {
            configdata = new ConfigPropFileData();
        }
        return configdata;
    }

    public String getUrl() {
        return url;
    }

    public String getBrowser() {
        return browser;
    }


    public Properties readPropertiesFile() {
        FileInputStream fileInputStream;
        Properties prop = null;
        try {
            String filepath = System.getProperty("user.dir") + "/data/commondata.properties";
            File fileName = new File(filepath);
            fileInputStream = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (IOException ioException) {
            LOGGER.error("Error while reading properties file", ioException);
        }
        return prop;
    }
}