Feature: Retrieve previous orders
  Scenario:
    Given A customer
    When I view my order history for food orders
    Then I can keep track of my past orders and preferences