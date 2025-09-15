@ui
Feature: Login Functionality
  As a user of the application
  I want to be able to login with my credentials
  So that I can access my account

  Background:
    Given I am on the login page

  @smoke
  Scenario: Successful login with valid credentials
    When I enter username "testuser" and password "testpass"
    And I click on the login button
    Then I should be logged in successfully
    And I should be redirected to the dashboard page

  @regression
  Scenario Outline: Unsuccessful login with invalid credentials
    When I enter username "<username>" and password "<password>"
    And I click on the login button
    Then I should see an error message "<error_message>"

    Examples:
      | username | password | error_message           |
      | invalid  | testpass | Invalid username        |
      | testuser | invalid  | Invalid password        |
      |          | testpass | Username cannot be empty |
      | testuser |          | Password cannot be empty |