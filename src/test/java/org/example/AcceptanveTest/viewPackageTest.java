package org.example.AcceptanveTest;
import APP.packageDetails;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class viewPackageTest {
    @Given("I have a serial number of a package")
    public void iHaveASerialNumberOfAPackage() {

    }
    @When("I request to view the details of that package")
    public void iRequestToViewTheDetailsOfThatPackage() {

    }
    @Then("the system should display the full information about the package")
    public void theSystemShouldDisplayTheFullInformationAboutThePackage() throws IOException {
String email="hasan@gmail.com";
        int serial=1;
      int budget=1800;
packageDetails op=new packageDetails();
op.theUserIsNotifiedOfTheBudgetInvalid1(budget);
assertTrue(op.theSystemShouldDisplayTheFullInformationAboutThePackage(serial,budget,email));
    }
    @Given("I have a list of suitable packages")
    public void iHaveAListOfSuitablePackages() {

    }
    @Given("the serial number of the package I'm interested in is not in the list")
    public void theSerialNumberOfThePackageIMInterestedInIsNotInTheList() {

    }
    @When("I try to view the details of that package")
    public void iTryToViewTheDetailsOfThatPackage() {

    }
    @Then("the system should inform me that the specified package is not in the list")
    public void theSystemShouldInformMeThatTheSpecifiedPackageIsNotInTheList() throws IOException {
        String email="hasan@gmail.com";
        int serial=2;
        int budget=1800;
        packageDetails o=new packageDetails();
        o.theUserIsNotifiedOfTheBudgetInvalid1(budget);
        assertFalse(o.theSystemShouldDisplayTheFullInformationAboutThePackage(serial,budget,email));
    }
    @Then("provide suggestions or alternatives")
    public void provideSuggestionsOrAlternatives() {

    }



}
