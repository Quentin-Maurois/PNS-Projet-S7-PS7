Feature: tests for AfterWorkOrder class
  Scenario: a customer makes an after work order
    Given an external customer who wants to make an after work order for 5 person with a serving of pasta at 8.5 € for each guest
    When they pay for their after work order
    Then the order is assigned to the customer
    And the order is assigned to the restaurant
    And the total price for the order is 42.5