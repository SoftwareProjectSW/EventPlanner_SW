package org.example.AcceptanveTest;

import APP.AddSP_App;
import APP.DeleteSP_App;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class addSPTest {

    AddSP_App app = new AddSP_App() ;
    String line = null;
    @When("the admin enters a valid complete service provider's information")
    public void the_admin_enters_a_complete_service_provider_s_information() {

        line = "Huda,6,huda@gamil.com,DJ,designer,cook";
        app= new AddSP_App(line);
        assertTrue(app.getIsSPLineValide());

    }
    @Then("the system adds the service provider's information")
    public void the_system_adds_the_service_provider_s_information() {
       // line = "Huda,6,huda@gamil.com,DJ,designer,cook";
       // app = new AddSP_App(line);
       // app.addLineToFile(line);
//     //   app.setSP_Added(true);
       // assertTrue(app.isSP_Added());

    }
    @Then("the system displays a message indicating that a service provider is successfully added")
    public void the_system_displays_a_message_indicating_that_a_service_provider_is_successfully_added() {

        // app.showMessage();

    }

    @When("the admin enters an incomplete details for the service provider")
    public void the_admin_enters_an_incomplete_details_for_the_service_provider() {

     //   line = "Huda,6";
     //   app= new AddSP_App(line);
      //  assertFalse(app.getIsSPLineValide());

    }
    @Then("the system shows a message to prompt the admin to provide all required information")
    public void the_system_prompts_the_admin_to_provide_all_required_information() {

      //  app.showMessageToEnterFullDetails();

    }


    @When("the admin attempts to add the same service provider again")
    public void the_admin_attempts_to_add_the_same_service_provider_again() {

      //  line = "Huda,6,huda@gamil.com,DJ,designer,cook";
      //  app= new AddSP_App(line);

    }
    @Then("the system does not save the service provider's information")
    public void the_system_does_not_save_the_service_provider_s_information() {

      //  app.addLineToFile(line);
      //  assertFalse(app.isSP_Added());

    }
    @Then("displays an error message indicating that the service provider already exists")
    public void displays_an_error_message_indicating_that_the_service_provider_already_exists() {

//        line = "Huda,6,huda@gamil.com,DJ,designer,cook";
//        app=new AddSP_App(line);
//        app.setSP_Added(false);
//        app.showMessage();

    }

}
