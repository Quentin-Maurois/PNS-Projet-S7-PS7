Feature: Modifying Products from the menu by the Restaurant
  Scenario: Restaurant can edit a product name
    Given the restaurant owner has a product called "Couscous"
    When he wants to edit the name of the product to "Couscous Royal"
    Then the product name is changed to "Couscous Royal"

  Scenario:Restaurant can edit a product price
    Given the restaurant owner has a product called "Couscous" with a price of 30.00 euros
    When he wants to edit the price of the product to 35.00 euros
    Then the product price is changed to 35.00 euros
