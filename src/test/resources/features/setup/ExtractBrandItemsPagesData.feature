@setup
@items
Feature: We need to extract the Brand Items Page Data

  Scenario: Extracting data from the brand's items in Famous Smoke
    Given I need to Setup the Features for the Items
    And I navigate to the page "https://www.famous-smoke.com/brand-list"
    And I crawl through the items of each brand
    When I store the extracted items data
    Then I should process the base features templates
    And I should process the items features templates