@api
Feature: User API
  As an API consumer
  I want to be able to manage user data
  So that I can integrate with the user management system

  Background:
    Given the API base URL is set

  @smoke
  Scenario: Get user details
    When I send a GET request to "/users/1"
    Then the response status code should be 200
    And the response should contain user details

  @regression
  Scenario: Create a new user
    Given I have the following user details:
      | name     | John Doe       |
      | email    | john@test.com  |
      | password | securePassword |
    When I send a POST request to "/users" with the user details
    Then the response status code should be 201
    And the response should contain the created user ID

  @regression
  Scenario: Update user details
    Given I have the following user details:
      | name  | Updated Name |
      | email | new@test.com |
    When I send a PUT request to "/users/1" with the user details
    Then the response status code should be 200
    And the response should contain updated user details

  @regression
  Scenario: Delete a user
    When I send a DELETE request to "/users/1"
    Then the response status code should be 204