@setup
Feature: We need to extract the Brand Page Data

  Scenario: Extracting data from famous smoke
    Given I need to Setup the Features
    And I navigate to the page "https://www.famous-smoke.com/brand-list"
    And I crawl through the list
    When I store the extracted data
    Then I should process the features