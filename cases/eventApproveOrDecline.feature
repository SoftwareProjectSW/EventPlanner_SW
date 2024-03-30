Feature: Organizer Manages Event

  Scenario: Review and Approve Event
    Given a list of pending events awaiting approval
    When the organizer accesses the event management system
    And selects an event to review
    And approves the event
    Then the event status should be updated to "Approved" in the events file
    And a notification should be sent to the customer and provider confirming event approval

  Scenario: Review and Decline Event
    Given a list of pending events awaiting approval
    When the organizer accesses the event management system
    And selects an event to review
    And declines the event
    Then the event status should be updated to "Declined" in the events file
    And a notification should be sent to the customer informing them of the event's rejection

  Scenario: Error occurs during update of free dates
    Given a list of free dates "2024-04-01" and booked dates "2024-04-02, 2024-04-03"
    And a list of all budgets "1000"
    When an error occurs while updating the free dates
    Then an error message should be logged

  Scenario: ServiceProviderIndex exceeds the size of freeDates
    Given a ServiceProviderIndex of 3
    And a date "2024-04-01"
    And a list of free dates with size 2
    And a SuperSPData object
    When the processFreeDates function is called
    Then it should return false
  Scenario: Send Email Successfully
    Given a recipient email "recipient@example.com"
    And a subject "Test Subject"
    And a message content "This is a test email"
    When the sendEmail function is called with the given parameters
    Then it should log "Email sent successfully to recipient@example.com"