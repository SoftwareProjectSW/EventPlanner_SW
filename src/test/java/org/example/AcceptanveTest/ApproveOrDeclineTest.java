package org.example.AcceptanveTest;

import app.ApproveApp;
import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.EmailConfig;
import org.example.Main;
import DataB.SuperSPData;
import app.ApproveApp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;



public class ApproveOrDeclineTest {
    ApproveApp app=new ApproveApp();
    @Given("a list of pending events awaiting approval")
    public void aListOfPendingEventsAwaitingApproval() {
        assertTrue(ApproveApp.aListOfPendingEventsAwaitingApproval());
    }
    @When("the organizer accesses the event management system")
    public void theOrganizerAccessesTheEventManagementSystem() {
        String y= String.valueOf(20);
        assertFalse(Main.selectsAnEventToReview(y));

    }
    @When("selects an event to review")
    public void selectsAnEventToReview() {
        String i= String.valueOf(4);
        assertTrue(Main.selectsAnEventToReview(i));

    }
    @When("approves the event")
    public void approvesTheEvent() {
        String id="4";
        String status="Approved";
        String date="1/4/2024";
        assertTrue(Main.changeEventStatus(id,status,date));
    }
    @Then("the event status should be updated to {string} in the events file")
    public void theEventStatusShouldBeUpdatedToInTheEventsFile(String string) {
        String id="4";
        String status="Approved";
        String date="1/4/2024";
        assertTrue(Main.changeEventStatus(id,status,date));
    }
    @Then("a notification should be sent to the customer and provider confirming event approval")
    public void aNotificationShouldBeSentToTheCustomerAndProviderConfirmingEventApproval() {
        String email= EmailConfig.getSenderEmail();
        String pass= EmailConfig.getPassword();

    }
    @When("declines the event")
    public void declinesTheEvent() {
        String id="4";
        String status="DECLINED";
        String date="7/4/2024";
        assertFalse(Main.changeEventStatus(id,status,date));
    }
    @Then("a notification should be sent to the customer informing them of the event's rejection")
    public void aNotificationShouldBeSentToTheCustomerInformingThemOfTheEventSRejection() {

    }


    @Test
    public void testUpdateFreeDates_IOException() throws Exception {
        // Prepare test data
        List<List<String>> freeDates = null; // Mock data
        List<List<String>> bookedDates = null; // Mock data
        List<String> allBudgets = null; // Mock data

        // Perform the test
        try {
            app.updateFreeDates(freeDates, bookedDates, allBudgets);
            // If the control reaches here, the test should fail
            System.out.println("Test failed: No exception was thrown when expected.");
        } catch (Exception e) {

            String expectedErrorMessage = "Error updating free dates:";
            String loggedMessage = e.getMessage(); // Get the logged error message
            if (!loggedMessage.startsWith(expectedErrorMessage)) {
                System.out.println("Test failed: Incorrect error message logged.");
            }
        }
    }

    @Test
    public void testfindAndProcessDate() throws Exception {

        String id = "5";
        String date = "8/5/2024";
        ArrayList<String> dates = new ArrayList<>();
        List<List<String>> bookedDates = new ArrayList<>();

        int serviceProviderIndex = 11;
        SuperSPData object = new SuperSPData();
        ApproveApp app = new ApproveApp();
        boolean f = ApproveApp.findAndProcessDate( id,  date, dates,  serviceProviderIndex,  object);

        assertFalse(f);

    }

}
