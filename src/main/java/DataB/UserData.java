package DataB;

import org.example.AdminClass;
import org.example.UserClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static List<UserClass> UserList;

    public UserData() {
        UserList = new ArrayList<>();
    }

    public static List<UserClass> getUserList() {
        return UserList;
    }

    public void readUserDataFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the data is formatted as "email,password"
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String email = parts[0].trim();
                    String password = parts[1].trim();
                    UserClass user = new UserClass(email, password);
                    UserList.add(user);
                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "there is error ", e);
        }
    }
}
