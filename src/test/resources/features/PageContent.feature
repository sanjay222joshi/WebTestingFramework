Feature: The Brand Page content must follow a set of rules

  Scenario: Validating data against the TestData table

  Scenario Outline:
    Given I select the url "<URL>" from the TestData
    When I navigate to the brand page
    Then the title should match the TestData
    And the title should be  under 70 characters
    And the canonical url should match the TestData
    And the meta description should not be empty
    And the meta description should match the TestData
    And the meta description should be between 145 and 165 characters
    And the header one should not be empty
    And the header one should match the TestData
    And the description should not be empty
    And the description should match the TestData
    And the breadcrumbs should not be empty
    And the breadcrumbs text should match the TestData
    And the breadcrumbs links should match the TestData

    Examples:
    | URL |
  <LOADURLS>








