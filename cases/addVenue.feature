Feature: Add New Venue
  As an organizer
  I want to be able to add a new venue to the system

  Scenario: Organizer adds a new venue
    When an Organizer fills in the required details for the new venue name "name", capacity 400, and price 500.0
    Then the new venue should be added to the system successfully


  Scenario Outline: Organizer adds a new venue with invalid inputs
    When an organizer fills in the form with invalid "<name>" or <capacity> or <price>
    Then the system should display an error message "<error_message>"


    Examples:
      | name         | capacity | price | error_message                        |
      | MissingName  | 300      | 100.0 | Please provide a name for the venue  |
      | ValidName    | -1       | 100.0 | Venue capacity must be valid         |
      | ValidName    | 300      | -1    | Venue price must be valid            |