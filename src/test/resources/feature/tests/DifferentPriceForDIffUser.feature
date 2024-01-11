Feature: Different price according to user type
  Scenario:
    Given a customer student
    When they pay their order
    Then they get the student discount

    Scenario:
      Given a customer faculty person
      When they pay their order
      Then they get faculty discount

    Scenario:
      Given a customer staff person
      When they pay their order
      Then they get staff discount