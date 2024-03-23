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