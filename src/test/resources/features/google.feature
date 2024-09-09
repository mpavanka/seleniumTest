Feature: Google Search Functionality

  @pavan
  Scenario: Searching on Google
    Given I open the browser
    When I navigate to "https://www.google.com"
    And I search for "Cucumber BDD"
    Then I should see search results
