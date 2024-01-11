Feature: Restaurant discountV1
  Scenario:
    Given a customer who has a discountV1 in a restaurant
    When they order in the restaurant
    Then their order is cheaper

  Scenario:
    Given a customer who does not have access to the discountsV1 in a restaurant
    When they order in the restaurant
    Then the customer does not get any discount