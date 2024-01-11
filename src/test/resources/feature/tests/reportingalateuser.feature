Feature: Report a late user by Delivery Personnel
  Scenario: Banning a customer who's consumed his last chance of delay
    Given a delivery person observes a delay by a user for an order
    When the delivery person reports the delay in the system and the delay counter of the customer has reached 0pts
    Then  customer get banned.


  Scenario: Giving a chance to a user who's late for his order
    Given aaa delivery person observes a delay by a user for an order
    When the delivery person reports the delay in the system, and the delay counter of the customer has not reached 0pts yet
    Then  the the delay counter is decremented by one point.

