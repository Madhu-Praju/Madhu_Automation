@homePage @login
Feature: Home Page Validation
  As a user
  I want to validate the presence of navigation buttons in the Home page
  So that I can ensure proper navigation functionality

  Background: Login to Application
    Given I Open url "facctList"
    And User validate Welcome page elements
    Given User click on login button on Welcome page
    And User validates welcome texts

@homePage @navigation
  Scenario Outline: Complete Home Page Validation Flow
    # Reusable login - replaces 7 lines of repetitive login steps
    Given I perform complete login to home page with <tenantId> <userID> <password>
    
    # Core Home Page Validation - Focus on actual test logic
    When user is on the Home page
    Then all navigation buttons should be present
    # Validate individual buttons without relaunching browser
    Then the "Home" button should be present
    And the "Home" button should be enabled
    Then the "Subscriptions" button should be present
    And the "Subscriptions" button should be enabled
    Then the "Roles" button should be present
    And the "Roles" button should be enabled
    Then the "Groups" button should be present
    And the "Groups" button should be enabled
    Then the "Users" button should be present
    And the "Users" button should be enabled
    Then the "Reports" button should be present
    And the "Reports" button should be enabled
    Then the "API Management" button should be present
    And the "API Management" button should be enabled
    Then the "Help" button should be present
    And the "Help" button should be enabled
    Then the "Notifications" button should be present
    And the "Notifications" button should be enabled
    Then the "System configuration" button should be present
    And the "System configuration" button should be enabled
    # Validate collapsible panel functionality
    And user collapses the left panel
    Then the left panel should be collapsed

    Examples:
      | tenantId | userID              | password       |
      | 'facctum'| 'facctListusername' | 'facctListPass'|




