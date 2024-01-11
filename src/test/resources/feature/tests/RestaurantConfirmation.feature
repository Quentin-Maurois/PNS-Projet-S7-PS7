Feature: Restaurant confirmation
  Scenario: Restaurant confirms the an order
    Given a restaurant which can accept an order
    When it recieves an order
    Then it will accept it
    Then the order will have status IN_PREPARATION
  Scenario: Restaurant denies the order
    When a restaurant which cannot accept an order
    When it recieves a new order
    Then it will deny it
    Then the order will have status DENIED
