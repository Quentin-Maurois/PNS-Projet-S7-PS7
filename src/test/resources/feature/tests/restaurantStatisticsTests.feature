Feature: restaurant statistics tests
  Scenario: restaurant with no orders is at 0
    Given a restaurant who has not sold any order
    When they access their statistics
    Then the total of orders is at 0
    And the total price for orders is at 0.0
    And the total discounted amount is at 0.0
  Scenario: restaurant with order but no discount
    Given a restaurant a restaurant who receives an order with two products of price 5.0 and 10.0 and no discount
    When they access their statistics
    Then the total of orders is at 1
    And the total price for orders is at 15.0
    And the average price for orders is at 15.0
    And the total discounted amount is at 0.0
  Scenario: restaurant with order and discount
    Given a restaurant a restaurant who receives an order with two products of price 5.0 and 10.0 and has discounts of 7
    When they access their statistics
    Then the total of orders is at 1
    And the total price for orders is at 13.95
    And the average price for orders is at 13.95
    And the total discounted amount is at 1.05
  Scenario: restaurant with order but no discount and customer is student
    Given a restaurant a restaurant who receives an order with two products of price 5.0 and 10.0 and no discount but from a student
    When they access their statistics
    Then the total of orders is at 1
    And the total price for orders is at 14.25
    And the average price for orders is at 14.25
    And the total discounted amount is at 0.75
  Scenario: product count
    Given a restaurant with products at price of 12.5 5.0 7.0 and 15.35
    When they access their statistics
    Then the total of products is 4
    And the average price for products is at 9.9625
  Scenario: get time at which orders are ordered
    Given a restaurant which received three orders at 12 and one at 19
    When they access their statistics
    Then the percentage of orders ordered at 12 is 0.75
    And the percentage of orders ordered at 19 is 0.25
    And the percentage of orders ordered at any time but 12 and 19 is 0.0
  Scenario: get favourite products
    Given a restaurant which received orders containing different products
    When they access their statistics
    Then the favourite products are correct