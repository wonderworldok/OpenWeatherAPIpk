Feature: Validating Place API's

  @verifyTemp
  Scenario Outline: View next seven days of max temp if greater than Predicted temperature
    Given I like to view next seven days of temperature with "<Lat>"  "<Lon>"
    When I look up weather forecast and returns status 200
    Then weather for "<Location>" is above <PredictedTemp> degree

    Examples:
      | Location       | Lat     | Lon     | PredictedTemp |
      | Sydney         | 33.86   | 151.20  | 15            |
      | Rio de Janeiro | 22.9068 | 43.1729 | 20            |
