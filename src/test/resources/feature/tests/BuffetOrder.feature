Feature: tests for class BuffetOrder
  Scenario: Ordering Buffet for a University Event
    Given a member of the university staff initiating a buffet order
    When they pay for the new order
    Then the order is an instance of BuffetOrder
    And the restaurants have received the order
    And The maximum number of attendees for the event is equal to the capacity of the formula
