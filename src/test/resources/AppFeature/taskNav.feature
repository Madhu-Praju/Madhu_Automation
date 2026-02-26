@tasknavigation
Feature: List Management Navigation
  As a user of the Factum platform
  I want to navigate to List Management functionality
  And validate card states using common validation feature

   Background: Login to Application
    Given I Open url "facctList"
    And User validate Welcome page elements
    Given User click on login button on Welcome page
    And User validates welcome texts
    
 @tasknavigation
  Scenario Outline: Common Card Validation
    # Login Process
    Given user enters the organization name <tenantId>
    And user presses continue key
    And user enters facctlist login id <userID>
    And user enters the Password <password>
    Then validate clickable forgot password text 
    And user presses continue key to Login to Home page
    Then user navigates to the Home page
    Then validate that the user is on the "Home" page
    
    # Validate all card states using common validation feature
    When User validate the card states
    Then "List Management" card should be enabled
    And "Customer Screening" card should be enabled
    # And "Transaction Screening" card should be disabled
    # And "Transaction Monitoring" card should be disabled
    When User click on "List Management" card
    Then validate that the user is on the "Dashboard" page
    # Now validate the home page elements
    Then all navigation buttons should be present on Dashboard page
    Then the "Dashboard" button should be present
    And the "Dashboard" button should be enabled
    Then the "Tasks" button should be present
    And the "Tasks" button should be enabled
    Then the "Search" button should be present
    And the "Search" button should be enabled
    Then the "Watchlist" button should be present
    And the "Watchlist" button should be enabled
    Then the "Data Export" button should be present
    And the "Data Export" button should be enabled
    Then the "Reports" button should be present
    And the "Reports" button should be enabled
    # And user collapses the left panel
    # Then the left panel should be collapsed
    # And user expands the left panel
    
    # User navigates to the Tasks button
    And validate different task navigation tasks count
    When User click on "Tasks" button
    Then validate that the user is on the "Tasks" page
    And user collapses the left panel

     Examples:
      | tenantId | userID              | password       |
      | 'facctum'| 'facctListusername' | 'facctListPass'|