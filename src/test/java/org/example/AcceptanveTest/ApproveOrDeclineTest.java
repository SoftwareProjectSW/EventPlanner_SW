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

import javax.mail.Transport;
import java.util.ArrayList;
import java.util.List;



public class ApproveOrDeclineTest {
    ApproveApp app = new ApproveApp();
    String email;

    @Given("a list of pending events awaiting approval")
    public void aListOfPendingEventsAwaitingApproval() {
        assertTrue(ApproveApp.aListOfPendingEventsAwaitingApproval());
    }

    @When("the organizer accesses the event management system")
    public void theOrganizerAccessesTheEventManagementSystem() {
        String y = String.valueOf(20);
        assertFalse(Main.selectsAnEventToReview(y));

    }

    @When("selects an event to review")
    public void selectsAnEventToReview() {
        String i = String.valueOf(4);
        assertTrue(Main.selectsAnEventToReview(i));

    }

    @When("approves the event")
    public void approvesTheEvent() {
        String id = "4";
        String status = "Approved";
        String date = "1/4/2024";
        assertTrue(Main.changeEventStatus(id, status, date));
    }

    @Then("the event status should be updated to {string} in the events file")
    public void theEventStatusShouldBeUpdatedToInTheEventsFile(String string) {
        String id = "4";
        String status = "Approved";
        String date = "1/4/2024";
        assertTrue(Main.changeEventStatus(id, status, date));
    }

    @Then("a notification should be sent to the customer and provider confirming event approval")
    public void aNotificationShouldBeSentToTheCustomerAndProviderConfirmingEventApproval() {
        String email = EmailConfig.getSenderEmail();
        String pass = EmailConfig.getPassword();

    }

    @When("declines the event")
    public void declinesTheEvent() {
        String id = "4";
        String status = "DECLINED";
        String date = "7/4/2024";
        assertFalse(Main.changeEventStatus(id, status, date));
    }

    @Then("a notification should be sent to the customer informing them of the event's rejection")
    public void aNotificationShouldBeSentToTheCustomerInformingThemOfTheEventSRejection() {

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
        boolean f = ApproveApp.findAndProcessDate(id, date, dates, serviceProviderIndex, object);

        assertFalse(f);

    }

    @Given("a list of free dates {string} and booked dates {string}")
    public void aListOfFreeDatesAndBookedDates(String string, String string2) {

    }

    @Given("a list of all budgets {string}")
    public void aListOfAllBudgets(String string) {

    }

    @When("an error occurs while updating the free dates")
    public void anErrorOccursWhileUpdatingTheFreeDates() {

    }

    @Then("an error message should be logged")
    public void anErrorMessageShouldBeLogged() {
        ;
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
        assertFalse(app.updateFreeDates(freeDates, bookedDates, allBudgets));

    }

    @Given("a ServiceProviderIndex of {int}")
    public void aServiceProviderIndexOf(Integer int1) {

    }

    @Given("a date {string}")
    public void aDate(String string) {

    }

    @Given("a list of free dates with size {int}")
    public void aListOfFreeDatesWithSize(Integer int1) {

    }

    @Given("a SuperSPData object")
    public void aSuperSPDataObject() {

    }

    @When("the processFreeDates function is called")
    public void theProcessFreeDatesFunctionIsCalled() {

    }

    @Then("it should return false")
    public void itShouldReturnFalse() {
        String id = "5";
        String date = "8/5/2024";
        List<List<String>> dates = new ArrayList<>();
        List<List<String>> bookedDates = new ArrayList<>();

        int serviceProviderIndex = 11;
        SuperSPData object = new SuperSPData();
        boolean f = ApproveApp.processFreeDates(id, date, dates, serviceProviderIndex, object);
        assertFalse(f);
    }

 




}