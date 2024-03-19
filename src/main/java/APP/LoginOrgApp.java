package APP;

import DataB.OrganizerData;
import org.example.Organizer;

public class LoginOrgApp {

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
        else System.out.println("Invalid email or password");



    }
    public void loggInCheck(String email, String password) {
        OrganizerData Org = new OrganizerData();
        String file = "DataForOrganizer.txt";

        for (Organizer temp : Org.getorganizersList()) {
            if (email.equals(temp.getEmail()) && password.equals(temp.getPassword())) {
                login();
                return;
            }

        }

    }
}
