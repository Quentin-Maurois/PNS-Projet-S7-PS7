Feature: A restaurant got a production's capacity
  Scenario:
    Given a Restaurant called "Le petit gueuleton"
    When I specify my production's capacity
    Then I cant accept more order than my production's capacity