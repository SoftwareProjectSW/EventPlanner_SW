Feature: Event Budget and Service Provider Availability

  Scenario: User Specifies Budget
    Given the user is logged into the system
    When prompted, the user enters their budget for the event
    Then the budget is recorded.

  Scenario: Adequate Budget, Available Date
    Given a sufficient budget provided by the user
    And the selected service provider has availability on the required date
    When the user submits the budget and date
    Then the event booking is tell now confirmed
    And the user is informed of the next steps for venue booking.

  Scenario: Adequate Budget, Unavailable Date
    Given a sufficient budget provided by the user
    And the selected service provider is unavailable on the required date
    Then the user is informed of the service provider's unavailability

  Scenario: Insufficient Budget
    Given an insufficient budget provided by the user
    When the user submits the budget
    Then the user is notified of the budget invalid
