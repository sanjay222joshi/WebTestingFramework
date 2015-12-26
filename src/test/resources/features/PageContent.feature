Feature: The Brand Page content must follow a set of rules

  Background:
    Given I select the url from the TestData
    When I navigate to the brand page

  Scenario: Check Brand Logo
    Then the logo should load and be visible