# WeatherDetail

## How to install:

Pull from Git project and go to destination folder ./AmpionWeatherTechChallenge

run: mvn clean test

## How to Run

On Terminal in Mac or Windows go to following folder

./AmpionWeatherTechChallenge

Run commands: mvn clean verify

## Objective: 

View next seven days of temperature if more than predicted temperature.

## Design
BDD Design
* Cucumber file specification
* StepDef to link Cucumber file 
* Page Objects to link Step Def
* Drier file to make API calls
* Utils file for common functions for Page Objects

### Step 1. 
OpenWeatherTemp.feature
  @verifyTemp
  Scenario Outline: View next seven days of max temp if greater than Predicted temperature
    Given I like to view next seven days of temperature with "<Lat>"  "<Lon>"
    When I look up weather forecast and returns status 200
    Then weather for "<Location>" is above <PredictedTemp> degree

    Examples:
      | Location       | Lat     | Lon     | PredictedTemp |
      | Sydney         | 33.86   | 151.20  | 15            |
      | Rio de Janeiro | 22.9068 | 43.1729 | 20            |
      
### Step 2. 
    Define Step Def functions as per pages. main purpose is Assertions is done here
  *   OpenWeatherTempStepDef

  
### Step 3. 
    OpenWeatherTempAction where logic is defined
* OpenWeatherTempAction
Below two functions that construct call for above two scenario calls:
* buildGetRequest
* getResponse
* validateNextSevnDaysTemp


### Step 4.
Main Logic:
* Utils
* global.properties defines baseWeatherUrl=https://api.weatherbit.io 
## Result
Result is saved in below file

/target/reports-html.html

### Step 5.
Cucumber report is generated in:

\target\cucumber-html-reports
List of temperature more than predicted temperature is printed on screen


 
 