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

       // email ="hello";
        //password = "e";
        LogApp.loggInCheck(email,password);
        assertFalse(LogApp.isLoggedIn());

    }

    @Then("the message appear to tell the admin what's wrong")
    public void theMessageAppearToTellTheAdminWhatSWrong() {
         email="";
        password="";
        LogApp.errorInLogin(email,password);
    }


    @Then("successful login")
    public void successfulLogin() {

        email="hala@gmail.com";
        password="123";
        LogApp.loggInCheck(email,password);
        assertTrue(LogApp.isLoggedIn());

    }


    @Given("the email is {string}")
    public void theEmailIs(String mail) {
        this.email=mail;
    }

}