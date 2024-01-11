Feature: Group orders
  Scenario: Create a group order
    Given a customer who payed for their order
    When they want to create a group order
    Then a new group order is created
    When The second customer want to join the group order
    Then their order is added to the group order
    When A delivery person gets the notification to pick an order up
    Then They need to know all the individial orders
