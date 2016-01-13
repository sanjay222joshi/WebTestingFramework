Feature: The breadcrumbs of all the Pages match the Test Data

  Scenario Outline: I will the Page Breadcrumbs
    Given I want to the check the content of the url "<URL>"
    When I navigate to the page
    Then the breadcrumbs should not be empty
    And the breadcrumbs text should match the Test Data
    And the breadcrumbs links should match the Test Data
    Examples:
      | URL |
      <LOAD_URLS>











