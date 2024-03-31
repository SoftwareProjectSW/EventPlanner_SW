package org.example.AcceptanveTest;

import app.ReserveVenueApp;
import io.cucumber.java.en.*;
import org.example.VenueClass;

import static org.junit.Assert.*;

public class addVenueTest {
    private ReserveVenueApp app = new ReserveVenueApp();
    private String venueName;
    private int venueCapacity;
    private double venuePrice;
    private boolean venueAddedSuccessfully;
    private String errorMessage;


    @When("an Organizer fills in the required details for the new venue name {string}, capacity {int}, and price {double}")
    public void an_organizer_fills_in_the_required_details_for_the_new_venue_name_capacity_and_price(String string, Integer int1, Double double1) {

        this.venueName = string;
        this.venueCapacity = int1;
        this.venuePrice = double1;


    }
    @Then("the new venue should be added to the system successfully")
    public void the_new_venue_should_be_added_to_the_system_successfully() {




    }

    @When("an organizer fills in the form with invalid MissingName or {int} or {double}")
    public void an_organizer_fills_in_the_form_with_invalid_missing_name_or_or(Integer int1, Double double1) {

        String venueName = null;
        Integer venueCapacity = 300;
        Double venuePrice = 100.0 ;
        String message = app.isValidVenueDetails(venueName,venueCapacity,venuePrice);
        assertEquals(message, "Please provide a name for the venue");


    }
    @Then("the system should display an error message {string}")
    public void the_system_should_display_an_error_message(String string) {

        String venueName = "Venue4";
        Integer venueCapacity = 200;
        Double venuePrice = -1.0 ;
        String message = app.isValidVenueDetails(venueName,venueCapacity,venuePrice);
        assertNotEquals(message, "valid");

    }

    @When("an organizer fills in the form with invalid ValidName or {int} or {double}")
    public void an_organizer_fills_in_the_form_with_invalid_valid_name_or_or(Integer int1, Double double1) {

        String venueName = "Venue4";
        Integer venueCapacity = -1;
        Double venuePrice = 100.0 ;
        String message = app.isValidVenueDetails(venueName,venueCapacity,venuePrice);
        assertEquals(message, "Venue capacity must be valid");


    }


    @When("an organizer fills in the form with invalid {string} or {int} or {double}")
    public void anOrganizerFillsInTheFormWithInvalidNameOrCapacityOrPrice(String name, int capacity, double price) {

        String venueName = "Venue4";
        Integer venueCapacity = 200;
        Double venuePrice = -1.0 ;
        String message = app.isValidVenueDetails(venueName,venueCapacity,venuePrice);
        assertEquals(message, "Venue price must be valid");
    }

}