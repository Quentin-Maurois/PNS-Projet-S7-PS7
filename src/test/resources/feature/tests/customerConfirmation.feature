Feature: Customer confirms delivery
  Scenario:
    Given a customer waiting his order
    When they receive the order
    And the driver has confirmed delivery
    Then the customer can confirm the delivery via the application
    And the order status is updated to DELIVERY_CONFIRMED