Feature: Setup Data and Features
  Scenario: We need this to run so that the other Features are processed
    Given I need cucumber to Setup the Urls and TestData
    When I first run the test
    Then I should process the features