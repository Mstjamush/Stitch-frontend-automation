
@addToDo
Feature: addToDo

  @landingPage @test
  Scenario: Validate correct landing page
    Given I am Testing Case : "1"
    And User is arrive to Landing Page
    Then I want to see the Stitch Todo List message


  @validLogin @test
  Scenario: User Want to add to do
    Given I am Testing Case : "2"
    And User is arrive to Landing Page
    And I have enter To do as "Test"
    When I press the Submit button
    Then I verify the Submit
