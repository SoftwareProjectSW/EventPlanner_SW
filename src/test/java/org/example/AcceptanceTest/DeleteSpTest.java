package org.example.AcceptanceTest;

import APP.DeleteSP_App;
import io.cucumber.java.en.*;

import static org.junit.Assert.*;

public class DeleteSpTest {

    DeleteSP_App app = new DeleteSP_App() ;
    String spName;
    @Given("the service provider exists in the system")
    public void the_service_provider_exists_in_the_system() {
        app.enterServiceProviderName("Hala");
        assertTrue(app.getSP_Exists());
    }
    @Given("he has a unique name")
    public void he_has_a_unique_name() {

      app.enterServiceProviderName("Hala");
      assertTrue(app.getIsUnique());
    }

    @When("the Admin enters the name of service provider who's name is {string}")
    public void the_admin_enters_the_name_of_service_provider_who_s_name_is(String serviceProviderName) {
        spName = serviceProviderName;

    }


    @Then("the system removes the selected service provider's information from the system")
    public void the_system_removes_the_selected_service_provider_s_information_from_the_system() {

        // Assuming systemUnderTest.removeServiceProviderByName() method removes the service provider
        app.removeServiceProviderByName(spName);
        // After removal, you can assert that the service provider's information has been removed from the system
        assertFalse(app.checkExistence(spName));
    }

    @Given("there are multiple service providers with the same name in the system")
    public void there_are_multiple_service_providers_with_the_same_name_in_the_system() {

        spName = "Huda";
        assertTrue(app.getCount(spName) > 1 );

    }
    @When("the admin selects the option to delete a service provider by name")
    public void the_admin_selects_the_option_to_delete_a_service_provider_by_name() {
        spName = "Huda";
        app.removeServiceProviderByName(spName);
        assertTrue(app.checkExistence(spName));
    }

    @Then("the system prompts the admin to enter the ID = {string}")
    public void the_system_prompts_the_admin_to_enter_the_id(String ID) {

        app.removeServiceProviderById("11");
        assertFalse(app.getIsRemoveByIdFlag());

    }



    @Given("the service provider does not exist in the system")
    public void the_service_provider_does_not_exist_in_the_system() {

        spName = "Faeq";
        app.enterServiceProviderName(spName);
        assertFalse(app.checkExistence(spName));

    }

    @Then("the system displays a message indicating no service providers are available for deletion")
    public void the_system_displays_a_message_indicating_no_service_providers_are_available_for_deletion() {
        app.printMessage();

    }

}
