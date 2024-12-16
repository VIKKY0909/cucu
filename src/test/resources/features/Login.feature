Feature: Login Functionality for SauceDemo

  @LoginPositive
  Scenario: Valid login with standard user
    Given the user is on the login page
    When the user enters valid credentials "standard_user" and "secret_sauce"
    Then the user should be redirected to the Products page

  @LoginNegative
  Scenario: Invalid login with incorrect username
    Given the user is on the login page
    When the user enters invalid credentials "invalid_user" and "secret_sauce"
    Then the user should see an error message "Epic sadface: Username and password do not match any user in this service"

  @LoginNegative
  Scenario: Login without username and password
    Given the user is on the login page
    When the user enters blank credentials
    Then the user should see an error message "Epic sadface: Username is required"
