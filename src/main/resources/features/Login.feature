Feature: Login functionality

  Scenario: Login to Hartron
    Given Expand the login via dropdown
    And Select "Email" from dropdown
    Then Login using "siva@gemini.com" and "abc@123"