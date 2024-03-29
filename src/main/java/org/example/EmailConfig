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
        }
    }

    public static String getSenderEmail() {
        return properties.getProperty("senderEmail");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }
}
