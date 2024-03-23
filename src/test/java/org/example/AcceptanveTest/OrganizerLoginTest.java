package org.example.AcceptanveTest;


import APP.LoginOrgApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizerLoginTest {

    static LoginOrgApp LogApp=new LoginOrgApp();
    String email ="",password ="";
    @Given("that the Organizer is not logged in the app")
    public void that_the_organizer_is_not_logged_in_the_app() {

        LogApp.logout();
        assertFalse(LogApp.isLoggedIn());

    }
    @Then("the Organizer will not login")
    public void the_organizer_will_not_login() {

        email = "";
        password = "";
        LogApp.loggInCheck(email,password);
        assertFalse(LogApp.isLoggedIn());

    }
    @Then("the message appear to tell the Organizer what's wrong")
    public void the_message_appear_to_tell_the_organizer_what_s_wrong() {

        email="";
        password="";
        LogApp.errorInLogin(email,password);


    }



}