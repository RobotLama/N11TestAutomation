@ui
@sellers
Feature: Sellers

  Scenario: User should be able to navigate to the seller's page
    Given user is on the "Sellers" Page
    When user clicks to "Tüm Mağazalar" button
    And user clicks to the "S" letter to filter sellers
    And user clicks to a seller randomly
    Then user should see that navigated to the desired seller's page