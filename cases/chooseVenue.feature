Feature: Venue Reservation

  Scenario: User has insufficient budget for any venue
    Given the user has a budget of $200.0
    And the user needs a venue with at least 50 chairs
    When the user tries to reserve a venue
    Then the system should display a message indicating insufficient budget

  Scenario: User enters valid budget but no valid size
    Given the user has a budget of $2000.0
    And the user needs a venue with at least 850 chairs
    When the user tries to reserve a venue
    Then the system should display a message indicating no venues available with the specified size

  Scenario: User enters budget and size, system displays valid venues
    Given the user has a budget of $1000.0
    And the user needs a venue with at least 130 chairs
    When the user tries to reserve a venue
    Then the system should display valid venues within the budget and size requirement






