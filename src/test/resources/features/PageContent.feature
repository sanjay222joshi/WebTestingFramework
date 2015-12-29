Feature: The content of all Pages must follow a set of rules

  Scenario Outline: I will validate the common elements of every page
    Given I want to the check the content of the url "<URL>"
    When I navigate to the page
    Then the title should match the TestData
    And the title should be  under 70 characters
    And the canonical url should match the TestData
    And the meta description should not be empty
    And the meta description should match the TestData
    And the meta description should be between 145 and 165 characters
    And the breadcrumbs should not be empty
    And the breadcrumbs text should match the TestData
    And the breadcrumbs links should match the TestData

    Examples:
      | URL |
      <LOAD_URLS>












