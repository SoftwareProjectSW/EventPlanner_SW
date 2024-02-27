package APP;

import DataB.AdminData;
import org.example.AdminClass;

import javax.swing.*;

public class LogInAsAdmin {
    private boolean loginFlag=false;
    public boolean isLoggedIn() {

        return loginFlag;
    }
    public void logout() {

        loginFlag=false;
    }
    public void login() {

        loginFlag = true;
    }
    public void errorInLogin(String e,String p) {
        if (e.isEmpty() || p.isEmpty()) {
            System.out.println("empty email or password");
        }
        else        System.out.println("Invalid email or password");



    }
    public void loggInCheck(String email, String password) {
        AdminData a=new AdminData();
        String file="DataForAdmin.txt";
        a.readAdminDataFromFile(file);
      for (AdminClass admin : a.getAdminList()) {
           if (email.equals(admin.getEmail()) && password.equals(admin.getPassword())) {
            login();
            return;
            }

       }

    }}