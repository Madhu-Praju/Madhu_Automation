@IBLRecordApproval
Feature: IBL Record Approval and Validation
 Background: Login to Application
    Given I Open url "facctList"
    And User validate Welcome page elements
    Given User click on login button on Welcome page

@IBLRecordApproval
Scenario Outline: IBL Record Approval and Validation
    # Login Process
    Given user enters the organization name <tenantId>
    And user presses continue key
    And user enters facctlist login id <userID>
    And user enters the Password <password>
    And user presses continue key to Login to Home page
    Then user navigates to the Home page
    Then validate that the user is on the "Home" page
    When User click on "List Management" card
    Then validate that the user is on the "Dashboard" page
    When User click on "Tasks" button
    Then validate that the user is on the "Tasks" page
    And user collapses the left panel
    Given User can see all main tabs on Tasks page
    When the Pending L1 tab is already active
    Then User click on sub tab INTERNAL RECORDS
    And User click on Double Arrow Right icon
    Then User Selects the latest record to approve
    And User Claim and Accept record and validate the results


  Examples:
      | tenantId | userID              | password       |
      | 'facctum'| 'facctListusername' | 'facctListPass'|