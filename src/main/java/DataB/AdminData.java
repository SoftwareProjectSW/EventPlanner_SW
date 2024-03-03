package DataB;

import org.example.AdminClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.List;

public class AdminData {
    private  ArrayList<AdminClass> adminList;
    private  String adminFile="DataForAdmin.txt";

    public AdminData() {
        adminList = new ArrayList<>();
    }

    public  ArrayList<AdminClass> getAdminList() {
        return adminList;
    }

    public  void readAdminDataFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(adminFile))) {
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
            e.printStackTrace();
        }
    }

}