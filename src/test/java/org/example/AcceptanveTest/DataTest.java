package org.example.AcceptanveTest;

import DataB.AdminData;
import DataB.EventData;
import DataB.OrganizerData;
import DataB.VenueData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DataTest {

    boolean y;
    ArrayList array ;
    Object obj;
    AdminData AdminData = new AdminData();
    EventData EventData = new EventData();
    VenueData VenueData=new VenueData();
    OrganizerData  OrganizerData = new OrganizerData();
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
    @Given("the method readVenueDataFromFile used incorrectly")
    public void theMethodReadVenueDataFromFileUsedIncorrectly() {
        ArrayList i = null;
        y = VenueData.readVenueDataFromFile(i);

    }
    @Then("an exception trace occurs on readVenueDataFromFile and return false")
    public void anExceptionTraceOccursOnReadVenueDataFromFileAndReturnFalse() {
assertFalse(y);
    }



}
