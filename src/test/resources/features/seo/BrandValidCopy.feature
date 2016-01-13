Feature: The Brand Pages have a Description matching the extracted Data

  Scenario Outline: I will validate the Brand Description
    Given I want to the check the content of the url "<URL>"
    And the url is from a brand page
    When I navigate to the page
    Then the description should not be empty
    And the description should match the TestData
    Examples:
      | URL |
  <LOAD_BRAND_URLS>