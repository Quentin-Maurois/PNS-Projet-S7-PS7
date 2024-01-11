Feature: Delete a product from the menu
  Scenario: Removing a product from the menu
    Given the restaurant owner wants to remove a product from the menu
    When they select the deletion option for that product
    Then  the product is removed from the menu