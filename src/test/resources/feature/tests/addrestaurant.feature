Feature: Add a restaurant to the system
  Scenario:
    Given a CampusAdministrator
    When I add a new restaurant to the System
    Then There is a new restaurant in the System's restaurant list