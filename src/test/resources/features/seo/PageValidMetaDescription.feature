@seo
Feature: The MetaDescription of all the Pages match the Test Data

  Scenario Outline: I will validate Page MetaDescription
    Given I want to the check the content of the url "<URL>"
    When I navigate to the page
    Then the meta description should match the Test Data
    Examples:
      | URL |
  <LOAD_URLS>












