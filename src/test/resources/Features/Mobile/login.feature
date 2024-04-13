Feature: This feature covers the scenario related to login Page

  Scenario Outline: To validate that user is able login to Product Page
    Given User opens the app
    And User selects the country as "Afghanistan"
    And User enters the "<name>"
    And User selects Gender as "Male"
    Then User click on Let's Shop Button
    And User select product "<product>" and add to cart

    Examples:
    |  name  | product         |
    | Rishab | Jordan Lift Off |