package APP;

import DataB.UserData;
import org.example.UserClass;


public class LogInAsUser {
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
        UserData a=new UserData();
        String file="usersData.txt";
        a.readUserDataFromFile(file);
        for (UserClass user : a.getUserList()) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                login();
                return;
            }

        }

    }
}
