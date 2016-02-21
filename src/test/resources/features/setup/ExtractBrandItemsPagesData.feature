@setup
@items
Feature: We need to extract the Brand Items Page Data

  Scenario: Extracting data from the brands in Famous Smoke
    Given I need to Setup the Features
    And I navigate to the page "https://www.famous-smoke.com/brand-list"
    And I crawl through the items of each brand
    When I store the extracted items data
    Then I should process the items features templates