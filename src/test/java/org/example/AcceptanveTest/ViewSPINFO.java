package org.example.AcceptanveTest;
import APP.LogInAsAdmin;
import APP.viewInfoSP;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class ViewSPINFO {
    static viewInfoSP view=new viewInfoSP();
    @Given("the admin is logged into the system")
    public void theAdminIsLoggedIntoTheSystem() {
String file="DataForAdmin.txt";
assertTrue(view.theAdminIsLoggedIntoTheSystem(file));
    }
    @When("the admin selects the option to view service provider information")
    public void theAdminSelectsTheOptionToViewServiceProviderInformation() {

    }
    @Then("the system displays a list of service providers with their details")
    public void theSystemDisplaysAListOfServiceProvidersWithTheirDetails() {
String n="Hala";
assertTrue(view. theSystemDisplaysAListOfServiceProvidersWithTheirDetails(n));
    }
    @Then("the admin can view details such as name, contact information, and services offered")
    public void theAdminCanViewDetailsSuchAsNameContactInformationAndServicesOffered() {
        assertTrue(view.theAdminCanViewDetailsSuchAsNameContactInformationAndServicesOffered());

    }
    @When("there are no service providers registered in the system")
    public void thereAreNoServiceProvidersRegisteredInTheSystem() {
        assertTrue(view.thereAreNoServiceProvidersRegisteredInTheSystem());
    }
    @Then("the system displays a message indicating no service providers are available")
    public void theSystemDisplaysAMessageIndicatingNoServiceProvidersAreAvailable() {
        assertTrue(view.thereAreNoServiceProvidersRegisteredInTheSystem());
    }



}
