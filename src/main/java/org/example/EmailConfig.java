package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EmailConfig {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            // Load properties from the config.properties file
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading config.properties file: " + e.getMessage());
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
