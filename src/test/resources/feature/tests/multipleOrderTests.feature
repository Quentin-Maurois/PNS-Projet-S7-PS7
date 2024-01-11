Feature: tests for class MultipleOrder
  Scenario: a customer makes an order with multiple restaurants
    Given a customer who makes an order with products from multiple restaurants
    When they pay for their new order
    Then the order is an instance of MultipleOrder
    And all the restaurants have received the order
    And the total price for each restaurant is coherent
