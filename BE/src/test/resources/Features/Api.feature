@BackEndTest

Feature: To use post methods of heruku/booking Public user api.

  Scenario: using the Post method for public user
    Given Get API is provided
    When User perform Post Operation
    When User perform Get Operation
    Then Status code should be 200
     And Verify the get call contains same payload data as posted