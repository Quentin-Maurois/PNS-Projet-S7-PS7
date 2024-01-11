Feature: deliveryPerson statistic tests
  Scenario: a delivery person with no orders has stats at 0
    Given a new delivery person
    When they check their statistics
    Then the total number of orders is at 0
    And the total price for their orders is at 0.0
    And the average price for their orders is at 0.00
  Scenario: a delivery person with one order
    Given a delivery person with an order of 15.0
    When they check their statistics
    Then the total number of orders is at 1
    And the total price for their orders is at 15.0
    And the average price for their orders is at 15.0
  Scenario: get time at which orders are assigned
    Given a delivery person who received three orders at 12 and one at 19
    When they check their statistics
    Then the percentage of orders assigned at 12 is 0.75
    And the percentage of orders assigned at 19 is 0.25
    And the percentage of orders assigned at any time but 12 and 19 is 0.0