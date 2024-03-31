package org.example.AcceptanveTest;

import DataB.AdminData;
import DataB.EventData;
import DataB.OrganizerData;
import app.ReserveVenueApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.VenueClass;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

public class DataTest {

    boolean y;
    ArrayList array ;
    Object obj;
    AdminData AdminData = new AdminData();
    EventData EventData = new EventData();
    OrganizerData  OrganizerData = new OrganizerData();
    ReserveVenueApp ReserveVenueApp = new ReserveVenueApp();
    @Given("the method readAdminDataFromFile used incorrectly")
    public void the_method_read_admin_data_from_file_used_incorrectly() {

        ArrayList i = null;
        y = AdminData.readAdminDataFromFile(i);

    }
    @Then("an exception trace occurs and return false")
    public void an_exception_trace_occurs_and_return_false() {

        assertFalse(y);

    }

    @Given("the method readEventsFromFile used incorrectly")
    public void the_method_read_events_from_file_used_incorrectly() {

        obj = DataB.EventData.createEventFromString("hi");
        ArrayList i = null;
        array = EventData.readEventsFromFile(i);
    }
    @Then("an exception trace occurs on readEventsFromFile and return false")
    public void an_exception_trace_occurs_on_read_events_from_file_and_return_false() {

        assertNull(obj);
        assertNull(array);

    }

    @Given("the method readOrgDataFromFile used incorrectly")
    public void the_method_read_org_data_from_file_used_incorrectly() {

        ArrayList i = null;
        y = OrganizerData.readOrgDataFromFile(i);

    }
    @Then("an exception trace occurs on readOrgDataFromFile and return false")
    public void an_exception_trace_occurs_on_read_org_data_from_file_and_return_false() {

        assertFalse(y);
    }
//////new

    @Given("the method getSelectedVenuePrice used incorrectly")
    public void the_method_get_selected_venue_price_used_incorrectly() {


    }
    @Then("an exception trace occurs on getSelectedVenuePrice and return false")
    public void an_exception_trace_occurs_on_get_selected_venue_price_and_return_false() {

        Double flag = Double.valueOf(ReserveVenueApp.getSelectedVenuePrice(800.0,100,1));
        assertTrue(flag!=-1.0);
        Double f = Double.valueOf(ReserveVenueApp.getSelectedVenuePrice((double) 800.0,100,12));
        assertTrue(f == -1.0);

    }


    @Given("the method getVenueWithBudget used incorrectly")
    public void the_method_get_venue_with_budget_used_incorrectly() {


    }
    @Then("an exception trace occurs on getVenueWithBudget and return false")
    public void an_exception_trace_occurs_on_get_venue_with_budget_and_return_false() {

        ArrayList<VenueClass> array = new ArrayList<>();
        array = ReserveVenueApp.getVenueWithBudget(800.0,100);

        assertTrue(array.size()!=0);


    }



    @Given("the method getSelectedVenue used incorrectly")
    public void the_method_get_selected_venue_used_incorrectly() {



    }
    @Then("an exception trace occurs on getSelectedVenue and return false")
    public void an_exception_trace_occurs_on_get_selected_venue_and_return_false() {

        String string =ReserveVenueApp.getSelectedVenue(800.0,100,1);
        assertTrue(!string.isEmpty());

    }


    @Given("the method isAddedVenue used incorrectly")
    public void the_method_is_added_venue_used_incorrectly() {



    }
    @Then("an exception trace occurs on isAddedVenue and return false")
    public void an_exception_trace_occurs_on_is_added_venue_and_return_false() {

        String venueName = null;
        Integer venueCapacity = 200;
        Double venuePrice = 1000.0 ;
        boolean flag = ReserveVenueApp.isAddedVenue( venueName,  venueCapacity,  venuePrice);
        assertFalse(flag);

        venueName = "Venue1";
        flag = ReserveVenueApp.isAddedVenue( venueName,  venueCapacity,  venuePrice);
        assertFalse(flag);

        venueName = "Venue56";
        flag = ReserveVenueApp.isAddedVenue( venueName,  venueCapacity,  venuePrice);
        assertTrue(flag);


    }



    @Given("the method isValidVenueDetails used incorrectly")
    public void the_method_is_valid_venue_details_used_incorrectly() {


    }
    @Then("an exception trace occurs on isValidVenueDetails and return false")
    public void an_exception_trace_occurs_on_is_valid_venue_details_and_return_false() {

        String venueName = "Venue4";
        Integer venueCapacity = 200;
        Double venuePrice = -1.0 ;
        String message = ReserveVenueApp.isValidVenueDetails(venueName,venueCapacity,venuePrice);
        assertNotEquals(message, "valid");

        venueName = null;
        venueCapacity = 300;
        venuePrice = 100.0 ;
        message = ReserveVenueApp.isValidVenueDetails(venueName,venueCapacity,venuePrice);
        assertEquals(message, "Please provide a name for the venue");

        venueName = "Venue4";
        venueCapacity = -1;
        venuePrice = 100.0 ;
        message = ReserveVenueApp.isValidVenueDetails(venueName,venueCapacity,venuePrice);
        assertEquals(message, "Venue capacity must be valid");

        venueName = "Venue4";
        venueCapacity = 200;
        venuePrice = -1.0 ;
        message = ReserveVenueApp.isValidVenueDetails(venueName,venueCapacity,venuePrice);
        assertEquals(message, "Venue price must be valid");


    }
    ///new

}
