@ui
@basket
Feature: Add products to the basket

  Scenario: User should be able to add first and last product to the basket
    Given user is on the "Home" Page
    When user searches "iPhone" in the search bar
    And user adds the "first" product to the basket on the "first" page
    And user adds the "last" product to the basket on the "first" page
    Then the number of products in the basket must be "2"