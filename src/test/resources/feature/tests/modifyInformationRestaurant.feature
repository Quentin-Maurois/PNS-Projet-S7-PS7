Feature: A restaurant can edit information about him
  Scenario:
    Given a restaurant called "MacD0nalds" based in "Sophia Antipolis"
    When I want to edit my name with "MacDonalds"
    Then my new name is correctly changed

  Scenario:
    Given a Restaurant called "MacDonals" based in "Biot"
    When I want to change my location for "Antibes"
    Then my new location is "Antibes"

  Scenario:
    Given a new restaurant called "MacDonalds" based in "Paris"
    When I want to add a new product called "Burger" which cost 15 euros
    Then my product's list is correctly updated

  Scenario:
    Given a restaurant called "Au bon manger"
    When I want to change my hours
    Then My new hours are all changed