Feature: Delete a Restaurant from the system
  Scenario:
    Given that I am an Campus Administrator viewing the list of restaurants
    When I choose to delete a specific restaurant
    Then  the chosen restaurant should be removed from the system's restaurant list
