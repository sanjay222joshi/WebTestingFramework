@seo
Feature: The MetaDescription length of all Pages is in a range

  Scenario Outline: I will validate the length of the Page MetaDescription
    Given I want to the check the content of the url "<URL>"
    When I navigate to the page
    Then the meta description should be over 145 characters
    And the meta description should be under 165 characters
    Examples:
      | URL |
  <LOAD_URLS>












