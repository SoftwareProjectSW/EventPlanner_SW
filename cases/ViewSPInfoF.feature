Feature: View Service Provider Information

  Scenario: Admin views service provider information
    Given the admin is logged into the system
    When the admin selects the option to view service provider information
    Then the system displays a list of service providers with their details
    And the admin can view details such as name, contact information, and services offered

  Scenario: Admin views service provider information with no available providers
    Given the admin is logged into the system
    When there are no service providers registered in the system
    Then the system displays a message indicating no service providers are available