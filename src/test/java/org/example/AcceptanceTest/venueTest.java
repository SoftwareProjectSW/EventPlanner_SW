package org.example.AcceptanceTest;

import APP.ReserveVenueApp;
import io.cucumber.java.en.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class venueTest {


    private Double budget;
    private Integer requiredSize;
    private ReserveVenueApp app;
    private boolean flag =false;
    @Given("the user has a budget of ${double}")
    public void the_user_has_a_budget_of_$(Double budget) {

        this.budget = budget;

    }
    @Given("the user needs a venue with at least {int} chairs")
    public void the_user_needs_a_venue_with_at_least_chairs(Integer requiredSize) {

        this.requiredSize = requiredSize;

    }
    @When("the user tries to reserve a venue")
    public void the_user_tries_to_reserve_a_venue() {

        app = new ReserveVenueApp();

    }
    @Then("the system should display a message indicating no venues available with the specified size")
    public void the_system_should_display_a_message_indicating_no_venues_available_with_the_specified_size() {

        budget = 2000.0;
        requiredSize = 850 ;
        flag = app.reserveVenue(budget,requiredSize);
        assertFalse(flag);

    }


    @Then("the system should display a message indicating insufficient budget")
    public void theSystemShouldDisplayAMessageIndicatingInsufficientBudget() {

        budget = 200.0;
        requiredSize = 50 ;
        flag = app.reserveVenue(budget,requiredSize);
        assertFalse(flag);

    }


    @Then("the system should display valid venues within the budget and size requirement")
    public void theSystemShouldDisplayValidVenuesWithinTheBudgetAndSizeRequirement() {

        budget = 1000.0;
        requiredSize = 130 ;
        flag = app.reserveVenue(budget,requiredSize);
        assertTrue(flag);

    }


}
