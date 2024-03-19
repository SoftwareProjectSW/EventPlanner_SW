Feature: Organizer login

  Scenario: Successfully login
    Given that the Organizer is not logged in the app
    And the email is "tala@gmail.com"
    And the password is "000"
    Then  successful login

  Scenario: wrong email entered
    Given that the Organizer is not logged in the app
    And the email is "wrong"
    And the password is "000"
    Then the Organizer will not login
    And the message appear to tell the Organizer what's wrong

  Scenario: wrong password entered
    Given that the Organizer is not logged in the app
    And the email is "tala@gmail.com"
    And the password is "wrong"
    Then the Organizer will not login
    And the message appear to tell the Organizer what's wrong

  Scenario: Organizer entered empty email or password
    Given that the Organizer is not logged in the app
    And the email is " "
    And the password is " "
    Then the Organizer will not login
    And the message appear to tell the Organizer what's wrong