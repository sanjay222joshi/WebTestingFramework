Feature: The Title of all the Pages match the Test Data

  Scenario Outline: I will validate the Page Title
    Given I want to the check the content of the url "<URL>"
    When I navigate to the page
    Then the title should match the Test Data
    Examples:
      | URL |
  <LOAD_URLS>











