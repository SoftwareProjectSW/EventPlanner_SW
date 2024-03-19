package APP;

import DataB.AdminData;
import org.example.AdminClass;

//import javax.swing.*;

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
        a.readAdminDataFromFile();
        for (AdminClass admin : a.getAdminList()) {
            if (email.equals(admin.getEmail()) && password.equals(admin.getPassword())) {
                login();
                return;
            }

        }

    }

    public class ColoredOutput {
        // ANSI escape codes for text colors
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";

        public static void main(String[] args) {
            // Print text in different colors
            System.out.println(ANSI_RED + "This text is red." + ANSI_RESET);
            System.out.println(ANSI_GREEN + "This text is green." + ANSI_RESET);
            System.out.println(ANSI_BLUE + "This text is blue." + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "This text is yellow." + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "This text is purple." + ANSI_RESET);
            System.out.println(ANSI_CYAN + "This text is cyan." + ANSI_RESET);
            System.out.println(ANSI_WHITE + "This text is white." + ANSI_RESET);
        }
    }


}