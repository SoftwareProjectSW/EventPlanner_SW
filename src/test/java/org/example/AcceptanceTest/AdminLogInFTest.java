package org.example.AcceptanceTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
//import org.example.AdminClass;
import APP.LogInAsAdmin;

import static org.junit.jupiter.api.Assertions.*;

public class AdminLogInFTest {

    static LogInAsAdmin LogApp=new LogInAsAdmin();
    String email,password;

    @Given("that the admin is not logged in the app")
    public void thatTheAdminIsNotLoggedInTheApp() {
        LogApp.logout();
        assertFalse(LogApp.isLoggedIn());

    }


    @Given("the password is {string}")
    public void thePasswordIs(String pass) {
        this.password=pass;
    }


    @Then("the admin will not login")
    public void theAdminWillNotLogin() {
        LogApp.loggInCheck(email,password);
        assertFalse(LogApp.isLoggedIn());

    }

    @Then("the message appear to tell the admin what's wrong")
    public void theMessageAppearToTellTheAdminWhatSWrong() {
        String email="";
        String pass="";
        LogApp.errorInLogin(email,pass);
    }


    @Then("successful login")
    public void successfulLogin() {

        String  e="hala@gmail.com";
        String p="123";
        LogApp.loggInCheck(e,p);
        assertTrue(LogApp.isLoggedIn());

    }


    @Given("the email is {string}")
    public void theEmailIs(String mail) {
        this.email=mail;
    }

}