package DataB;

import app.LoggerUtility;
import org.example.AdminClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;


public class AdminData {
    private static final Logger logger = LoggerUtility.getLogger();

    private static List<AdminClass> adminList;

    public AdminData() {
        adminList = new ArrayList<>();
    }

    public static List<AdminClass> getAdminList() {
        return adminList;
    }

    public void readAdminDataFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the data is formatted as "email,password"
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String email = parts[0].trim();
                    String password = parts[1].trim();
                    AdminClass admin = new AdminClass(email, password);
                    adminList.add(admin);
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "there is error ", e);

        }
    }

}