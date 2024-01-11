Feature: Pay for an order
  Scenario: Pay for an order
    Given a customer with a completed product list
    When they want to pay
    Then the corresponding order is successfully created
    Then the created order is assigned to the customer
    Then the created order is assigned to the restaurant
    Then the created order is assigned to the delivery person