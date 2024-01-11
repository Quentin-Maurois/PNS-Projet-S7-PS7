Feature: Add a product to a pendingOrder
  Scenario:
    Given a customer
    When they add a product to their pendingOrder
    Then  the product is stored in their pendingOrder