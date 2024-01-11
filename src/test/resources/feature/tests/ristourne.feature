Feature: Restaurant Discount
  Scenario:
    Given a customer who has a discount in a restaurant
    When they order in this restaurant
    Then the order is cheaper

  Scenario:
    Given a customer who misses a single order to get access to the discounts in a restaurant
    When they order in this restaurant
    Then the customer gets the discount in this restaurant (not for this order)

  Scenario:
  Given a customer who has a discount in a restaurant
  When the discount end date is reached
  Then the customer does not get the discount in this restaurant