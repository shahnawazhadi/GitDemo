Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI

Given Add Place Payload "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
     |name            |language | address                  |
     |Frontline house |French-IN |29, side layout, cohen 09|
     #|Washington house |English-IN |janpool chowk|
    # |delhi house | Germany-DE| Germany berlin |
 @DeletePlace   @Regression
 Scenario: Verify if Delete place functionality is working
       Given DeletePlace Payload
       When user calls "deletePlaceAPI" with "Post" http request
       Then the API call got success with status code 200
       #And "status" in response body is "OK"

       