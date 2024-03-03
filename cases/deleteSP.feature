Feature: Delete Service Provider Information.The Admin logs in successfully,
  his menu will show up, one of the actions he can take is to delete a service provider from the system.

  Scenario: Admin want to delete the information of a service provider by entering his unique name
    Given the service provider exists in the system
    And  he has a unique name
    When the Admin enters the name of service provider who's name is "name"
    Then the system removes the selected service provider's information from the system

  Scenario: Admin wants to delete a service provider by name, but multiple providers match the search criteria
    Given there are multiple service providers with the same name in the system
    When the admin selects the option to delete a service provider by name
    And the Admin enters the name of service provider who's name is "name"
    Then the system prompts the admin to enter the ID = "ID"

  Scenario: Admin attempts to delete non-existent service provider
    Given the service provider does not exist in the system
    When the Admin enters the name of service provider who's name is "name"
    Then the system displays a message indicating no service providers are available for deletion

