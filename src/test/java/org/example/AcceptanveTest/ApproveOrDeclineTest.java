package org.example.AcceptanveTest;

import app.ApproveApp;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class ApproveOrDeclineTest {
    ApproveApp app=new ApproveApp();
    @Given("a list of pending events awaiting approval")
    public void aListOfPendingEventsAwaitingApproval() {
assertTrue(app.aListOfPendingEventsAwaitingApproval());
    }
    @When("the organizer accesses the event management system")
    public void theOrganizerAccessesTheEventManagementSystem() {

    }
    @When("selects an event to review")
    public void selectsAnEventToReview() {
String i= String.valueOf(4);
assertTrue(app.selectsAnEventToReview(i));
    }
    @When("approves the event")
    public void approvesTheEvent() {
String id="4";
String status="Approved";
String date="1/4/2024";
assertTrue(app.changeEventStatus(id,status,date));
    }
    @Then("the event status should be updated to {string} in the events file")
    public void theEventStatusShouldBeUpdatedToInTheEventsFile(String string) {
        String id="4";
        String status="Approved";
        String date="1/4/2024";
        assertTrue(app.changeEventStatus(id,status,date));
    }
    @Then("a notification should be sent to the customer and provider confirming event approval")
    public void aNotificationShouldBeSentToTheCustomerAndProviderConfirmingEventApproval() {

    }
    @When("declines the event")
    public void declinesTheEvent() {
        String id="4";
        String status="Decline";
        String date="8/4/2024";
        assertFalse(app.changeEventStatus(id,status,date));
    }
    @Then("a notification should be sent to the customer informing them of the event's rejection")
    public void aNotificationShouldBeSentToTheCustomerInformingThemOfTheEventSRejection() {

    }



}
