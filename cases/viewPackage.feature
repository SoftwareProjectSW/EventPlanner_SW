Feature: View Package Information

  Scenario: View details of a specific package
    Given I have a serial number of a package
    When I request to view the details of that package
    Then the system should display the full information about the package


  Scenario: View details of a package when the serial number is not in the list
    Given I have a list of suitable packages
    And the serial number of the package I'm interested in is not in the list
    When I try to view the details of that package
    Then the system should inform me that the specified package is not in the list
    And provide suggestions or alternatives
