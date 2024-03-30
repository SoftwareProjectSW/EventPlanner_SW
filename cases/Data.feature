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

  Scenario: an exception occurs on EventData class
    Given the method readVenueDataFromFile used incorrectly
    Then an exception trace occurs on readVenueDataFromFile and return false