Feature: data coverage

  Scenario: an exception occurs on AdminData class
    Given the method readAdminDataFromFile used incorrectly
    Then an exception trace occurs and return false

  Scenario: an exception occurs on EventData class
    Given the method readEventsFromFile used incorrectly
    Then an exception trace occurs on readEventsFromFile and return false

  Scenario: an exception occurs on OrganizerData class
    Given the method readOrgDataFromFile used incorrectly
    Then an exception trace occurs on readOrgDataFromFile and return false

  Scenario: an exception occurs on ReserveVenueApp class
    Given the method getSelectedVenuePrice used incorrectly
    Then an exception trace occurs on getSelectedVenuePrice and return false

  Scenario: an exception occurs on ReserveVenueApp class
    Given the method getVenueWithBudget used incorrectly
    Then an exception trace occurs on getVenueWithBudget and return false

  Scenario: an exception occurs on ReserveVenueApp class
    Given the method getSelectedVenue used incorrectly
    Then an exception trace occurs on getSelectedVenue and return false

  Scenario: an exception occurs on ReserveVenueApp class
    Given the method isAddedVenue used incorrectly
    Then an exception trace occurs on isAddedVenue and return false

  Scenario: an exception occurs on ReserveVenueApp class
    Given the method isValidVenueDetails used incorrectly
    Then an exception trace occurs on isValidVenueDetails and return false

