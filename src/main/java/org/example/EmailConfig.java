package org.example;

import app.LoggerUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

public class EmailConfig {
    private static final Logger logger = LoggerUtility.getLogger();

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            // Load properties from the config.properties file
            FileInputStream fis = new FileInputStream("config.properties.yml");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "there is error ", e);
        }
    }

    public static String getSenderEmail() {
        String senderEmail = properties.getProperty("senderEmail");
        if (senderEmail == null) {
            System.err.println("senderEmail property is not set in config.properties");
        }
        return senderEmail;
    }

    public static String getPassword() {
        String password = properties.getProperty("password");
        if (password == null) {
            System.err.println("password property is not set in config.properties");
        }
        return password;
    }


}
