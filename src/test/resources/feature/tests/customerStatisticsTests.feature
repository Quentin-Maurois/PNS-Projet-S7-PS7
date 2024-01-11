Feature: customer statistics tests
  Scenario: Customer fetches stats with no orders
    Given A customer who never ordered
    When they access their statistics page
    Then they should see that the statistics are at zero
  Scenario: Customer with Order
    Given A customer who has successfully placed an order
    When they pay for their order
    Then in their statistics the price is changed accordingly
    And in their statistics the number of orders is set to 1
  Scenario: Multiple Orders
    Given A customer with a history of one order
    When they pay for a new order
    Then in their statistics the price is aggregated accordingly
    And in their statistic the number of orders is set to 2
  Scenario: Customer with a discountV1
    Given a customer with a discountV1 in a restaurant
    When they pay for the order
    Then the price in the stats is changed accordingly to the discountV1
  Scenario: Customer with a discount
    Given a customer who has a discount in the restaurant
    When they pay for this order
    Then the price in the stats is changed accordingly to the discount
  Scenario: Customer who is a student
    Given a customer who is a student
    When they pay for an order
    Then the price in the stats is less according to the student discount