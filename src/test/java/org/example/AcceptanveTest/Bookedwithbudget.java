package org.example.AcceptanveTest;

import app.AddSP_App;
import app.DeleteSP_App;
import app.EnteredBudget;
import io.cucumber.java.en.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class Bookedwithbudget {
    static EnteredBudget book = new EnteredBudget();

    //1
    @Given("the user is logged into the system")
    public void theUserIsLoggedIntoTheSystem() {

    }
    @When("prompted, the user enters their budget for the event")
    public void promptedTheUserEntersTheirBudgetForTheEvent() {

    }
    @Then("the budget is recorded.")
    public void theBudgetIsRecorded() {

    }
//2
    @Given("a sufficient budget provided by the user")
    public void aSufficientBudgetProvidedByTheUser() {
String budget="3500$";
assertTrue(book.aSufficientBudgetProvidedByTheUser(budget));

    }
    @Given("the selected service provider has availability on the required date")
    public void theSelectedServiceProviderHasAvailabilityOnTheRequiredDate() {
String budget="3500$";
assertTrue(book.theSelectedServiceProviderHasAvailabilityOnTheRequiredDate(budget));
    }
    @When("the user submits the budget and date")
    public void theUserSubmitsTheBudgetAndDate() {
        int id=4;
assertTrue(book.theUserSubmitsTheBudgetAndDate(id));
    }
    @Then("the event booking is tell now confirmed")
    public void theEventBookingIsTellNowConfirmed() {

    }
    @Then("the user is informed of the next steps for venue booking.")
    public void theUserIsInformedOfTheNextStepsForVenueBooking() {

    }
    //3

    @Given("the selected service provider is unavailable on the required date")
    public void theSelectedServiceProviderIsUnavailableOnTheRequiredDate() {

    }
    @Then("the user is informed of the service provider's unavailability")
    public void theUserIsInformedOfTheServiceProviderSUnavailability() {

    }

//4
    @Given("an insufficient budget provided by the user")
    public void anInsufficientBudgetProvidedByTheUser() {
String b="1000$";
assertFalse(book.theSelectedServiceProviderHasAvailabilityOnTheRequiredDate(b));
    }
    @When("the user submits the budget")
    public void theUserSubmitsTheBudget() {

    }
    @Then("the user is notified of the budget invalid")
    public void theUserIsNotifiedOfTheBudgetInvalid() throws IOException {
        int price=2000;
        assertTrue(book.theUserIsNotifiedOfTheBudgetInvalid(price));
    }


}
