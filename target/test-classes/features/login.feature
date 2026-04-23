Feature: Login Automation with Excel and Performance Test

  Scenario: Execute login for all users and run performance test
    Given browser is launched
    When user logs in using Excel data
    Then performance test is executed