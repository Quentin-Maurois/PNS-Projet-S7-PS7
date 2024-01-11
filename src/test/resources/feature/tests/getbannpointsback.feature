Feature: A customer gets bann points back
    Scenario: A customer gets bann points back
      Given a customer who has already been signaled
      And the customer has not reached full ban points
      When they order their third order in a row
      Then they get their bann points back