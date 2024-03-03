Feature: admin login

  Scenario: Successfully login
    Given that the admin is not logged in the app
    And the email is "hala"
    And the password is "000"
    Then  successful login

  Scenario: wrong email entered
    Given that the admin is not logged in the app
    And the email is "wrong"
    And the password is "000"
    Then the admin will not login
    And the message appear to tell the admin what's wrong

  Scenario: wrong password entered
    Given that the admin is not logged in the app
    And the email is "hala"
    And the password is "wrong"
    Then the admin will not login
    And the message appear to tell the admin what's wrong

  Scenario: Admin entered empty email or password
    Given that the admin is not logged in the app
    And the email is " "
    And the password is " "
    Then the admin will not login
    And the message appear to tell the admin what's wrong