Feature: Manage Service Providers in Event Planner System
  As an administrator or authorized user of the Event Planner System,
  I want to be able to manage service providers within the system
  so that they can offer their services for events effectively.

  Scenario: Add a New Service Provider
    When the admin enters a valid complete service provider's information
    Then the system adds the service provider's information
    And the system displays a message indicating that a service provider is successfully added

  Scenario: Attempt to Add Service Provider with Incomplete Information
    When the admin enters an incomplete details for the service provider
    Then the system shows a message to prompt the admin to provide all required information

  Scenario: Attempt to Add an Existing Service Provider
    When the admin attempts to add the same service provider again
    Then the system does not save the service provider's information
    And displays an error message indicating that the service provider already exists

