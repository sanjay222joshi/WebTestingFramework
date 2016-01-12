Feature: The content of every Brand Page must follow a set of rules

  Scenario Outline: I will validate the content of brand pages
    Given I want to the check the content of the url "<URL>"
    And the url is from a brand page
    When I navigate to the page
    Then the header one should not be empty
    And the header one should match the TestData
    And the description should not be empty
    And the description should match the TestData
    And the brand logo should be visible

    Examples:
      | URL |
      <LOAD_BRAND_URLS>