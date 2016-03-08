@setup
@brands
Feature: We need to extract the Brand Page Data

  Scenario: Extracting data from famous smoke
    Given I need to Setup the Features for the Brands
    And I navigate to the page "https://www.famous-smoke.com/brand-list"
    And I crawl through the brands list
    When I store the extracted brands data
    Then I should process the base features templates
    And I should process the brands features templates