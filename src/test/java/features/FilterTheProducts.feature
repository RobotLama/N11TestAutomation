@ui
@filter
Feature: Filter and order products

  Scenario: User should be able to filter by Brand and Shipping Option and sort by Reviews
    Given user is on the "Home" Page
    When user searches "telefon" in the search bar
    And user selects the "second" option from the "Marka" filter
    And user selects the "Yorum sayısı" option from the sort by menu
    And user selects the option as "Ücretsiz Kargo" from the "Kargo Seçenekleri" filter
    Then all of the products should have "Ücretsiz Kargo" badge
