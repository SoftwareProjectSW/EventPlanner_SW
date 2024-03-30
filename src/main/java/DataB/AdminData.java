package DataB;

import app.LoggerUtility;
import org.example.AdminClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class AdminData {
    private static final Logger logger = LoggerUtility.getLogger();

    private static ArrayList<AdminClass> adminList;
    private  String adminFile="DataForAdmin.txt";

    public AdminData() {
        adminList = new ArrayList<>();
    }

    public static ArrayList<AdminClass> getAdminList() {
        return adminList;
    }

    public  boolean readAdminDataFromFile(ArrayList i) {
        try (BufferedReader br = new BufferedReader(new FileReader(adminFile))) {
            int r =i.size();
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
            return true;
        } catch (Exception  e) {
            logger.log(Level.SEVERE, "there is error ", e);

        }
        return false;
    }

}