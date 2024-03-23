Feature: user login

  Scenario: Successfully login
    Given that the user is not logged in the app
    And the email is "fadi"
    And the password is "000"
    Then  successful login

  Scenario: wrong email entered
    Given that the user is not logged in the app
    And the email is "wrong"
    And the password is "000"
    Then the user will not login
    And the message appear to tell the user what's wrong

  Scenario: wrong password entered
    Given that the user is not logged in the app
    And the email is "fadi"
    And the password is "wrong"
    Then the user will not login
    And the message appear to tell the user what's wrong

  Scenario: user entered empty email or password
    Given that the user is not logged in the app
    And the email is " "
    And the password is " "
    Then the user will not login
    And the message appear to tell the user what's wrong