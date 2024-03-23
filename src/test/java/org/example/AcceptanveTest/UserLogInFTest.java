package org.example.AcceptanveTest;
import APP.LogInAsAdmin;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.UserClass;
import APP.LogInAsUser;
import org.junit.Ignore;

import static org.junit.jupiter.api.Assertions.*;
public class UserLogInFTest {
    static LogInAsUser LogApp=new LogInAsUser();
    String email,password;
    @Given("that the user is not logged in the app")
    public void thatTheUserIsNotLoggedInTheApp() {
        LogApp.logout();
        assertFalse(LogApp.isLoggedIn());
        email = "example@example.com";
    }


    @Then("the user will not login")
    public void theUserWillNotLogin() {
        // Assuming email is available in the scenario
        LogApp.loggInCheck(email, password);
        assertFalse(LogApp.isLoggedIn());
    }

    @Then("the message appear to tell the user what's wrong")
    public void theMessageAppearToTellTheUserWhatSWrong() {
        String email="";
        String pass="";
        LogApp.errorInLogin(email,pass);
    }

}
