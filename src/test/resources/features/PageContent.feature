Feature: The Brand Page content must follow a set of rules

  Scenario: Validating data against the TestData table

  Scenario Outline:
    Given I select the url "<URL>" from the TestData
    When I navigate to the brand page
    Then the title should match the TestData
    And the canonical url should match the TestData
    And the meta description should match the TestData
    And the header one should match the TestData
    And the description should match the TestData
    And the breadcrumbs should match the TestData
    And the breadcrumbs links should match the TestData

    Examples:
    | URL |
  <LOADURLS>








