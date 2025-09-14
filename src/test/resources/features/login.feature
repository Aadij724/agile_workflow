Feature: User Login

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters valid username and password
    And clicks the login button
    Then the user should be redirected to the dashboard

  Scenario: Failed login with invalid credentials
    Given the user is on the login page
    When the user enters invalid username or password
    And clicks the login button
    Then the user should see an error message

  Scenario: Forgot password functionality
    Given the user is on the login page
    When the user clicks on the "Forgot Password" link
    Then the user should be redirected to the password reset page