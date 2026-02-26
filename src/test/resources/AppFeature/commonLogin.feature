@commonLogin
Feature: Common Login Functionality
  As a user
  I want to have reusable login steps
  So that I can avoid repeating login logic across different feature files

  Background: Common Login Steps
    Given I Open url "facctList"
    And User validate Welcome page elements
    Given User click on login button on Welcome page
    And User validates welcome texts

  @login @smoke
  Scenario Outline: Complete Login Flow
    Given user enters the organization name <tenantId>
    And user presses continue key
    And user enters facctlist login id <userID>
    And user enters the Password <password>
    Then validate clickable forgot password text 
    And user presses continue key to Login to Home page
    Then user navigates to the Home page
    Then validate that the user is on the "Home" page
    Then user validates the tenant name <tenantId>
    Then user validates the login id <userID>

    Examples:
      | tenantId | userID              | password       |
      | 'facctum'| 'facctListusername' | 'facctListPass'|

  @loginAndNavigateToHome
  Scenario Outline: Reusable Login to Home Page
    Given user enters the organization name <tenantId>
    And user presses continue key
    And user enters facctlist login id <userID>
    And user enters the Password <password>
    And user presses continue key to Login to Home page
    Then user navigates to the Home page
    Then validate that the user is on the "Home" page

    Examples:
      | tenantId | userID              | password       |
      | 'facctum'| 'facctListusername' | 'facctListPass'|

  @quickLogin
  Scenario Outline: Quick Login - Single Step
    Given I perform complete login with <tenantId> <userID> <password>

    Examples:
      | tenantId | userID              | password       |
      | 'facctum'| 'facctListusername' | 'facctListPass'|

  @quickLoginToHome
  Scenario Outline: Quick Login to Home Page - Single Step
    Given I perform complete login to home page with <tenantId> <userID> <password>

    Examples:
      | tenantId | userID              | password       |
      | 'facctum'| 'facctListusername' | 'facctListPass'|

  @quickLoginWithValidation
  Scenario Outline: Quick Login with Full Validation - Single Step
    Given I perform complete login with validation using <tenantId> <userID> <password>

    Examples:
      | tenantId | userID              | password       |
      | 'facctum'| 'facctListusername' | 'facctListPass'|
