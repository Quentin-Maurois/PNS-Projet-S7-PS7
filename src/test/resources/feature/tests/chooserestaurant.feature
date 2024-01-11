Feature: Search a restaurant
  Scenario:
    Given I am a simple customer
    When I search for a specific restaurant named McDonalds
    Then The system should return the restaurant McDonalds