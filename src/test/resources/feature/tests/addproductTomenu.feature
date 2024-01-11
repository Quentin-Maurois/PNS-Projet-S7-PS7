Feature: Adding a new product to the menu
  Scenario:
    Given the restaurant manager is logged into the system
    When I add a new product with required details
    Then the new product is successfully added to the menu and is visible to customers.