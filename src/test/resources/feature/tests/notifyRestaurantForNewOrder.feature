Feature:Order receipt confirmation by the restaurant employee
  Scenario:
    Given a Customer ordering
    When they validate an order
    Then the restaurantEmployee receives a notification

  Scenario:
    Given a restaurant employee
    When they access a restaurant's to-do list
    Then they can see the customer's order
    Then they will confirm the receipt of the order