@taskValidation
Feature: Task Validation
As a user of the Factum platform
I want to validate the task page functionality
And ensure that the task navigation works correctly

 Background: Login to Application
    Given I Open url "facctList"
    And User validate Welcome page elements
    Given User click on login button on Welcome page

# @tabsValidation
# Scenario Outline: Task Page Tabs Validation
#     # Login Process
#     Given User perform complete login with <tenantId> <userID> <password>
#     When User click on "List Management" card
#     Then validate that the user is on the "Dashboard" page
#     When User click on "Tasks" button
#     Then validate that the user is on the "Tasks" page
#     And user collapses the left panel
#     Given User can see all main tabs on Tasks page
#     When the Pending L1 tab is already active
#     Then User prints list of tabs under "Pending L1" main tab
#     Then User validate all sub-tabs presence under active main tab
#     Then Pending L1 total task count
#     Then Validate Sub-tab External Records is already active
#     When User click on sub tab INTERNAL RECORDS
#     Then User validate that the INTERNAL RECORDS sub-tab is active
#     When User click on sub tab LISTS CONFIGURATION
#     Then User validate that the LISTS CONFIGURATION sub-tab is active
#     When User click on sub tab PRESS RELEASE RECORDS
#     Then User validate that the PRESS RELEASE RECORDS sub-tab is active
#     When User click on sub tab TEMPLATES
#     Then User validate that the TEMPLATES sub-tab is active
#     Then Sum of all sub-tab task counts should be equal to Pending L1 total task count
  
#     When User click on Pending L2 tab
#     Then User validate that the Pending L2 tab is active
#     And User prints list of tabs under "Pending L2" main tab
#     Then Pending L2 total task count
#     Then Validate Sub-tab External Records is already active
#     When User click on sub tab INTERNAL RECORDS
#     Then User validate that the INTERNAL RECORDS sub-tab is active
#     Then Sum of all sub-tab task counts should be equal to Pending L2 total task count

#     When User click on Rejected tab
#     Then User validate that the Rejected tab is active
#     And User prints list of tabs under "Rejected" main tab
#     Then Rejected total task count
#     Then Validate Sub-tab External Records is already active
#     When User click on sub tab INTERNAL RECORDS
#     Then User validate that the INTERNAL RECORDS sub-tab is active
#     When User click on sub tab LISTS CONFIGURATION
#     Then User validate that the LISTS CONFIGURATION sub-tab is active
#     When User click on sub tab PRESS RELEASE RECORDS
#     Then User validate that the PRESS RELEASE RECORDS sub-tab is active
#     When User click on sub tab TEMPLATES
#     Then User validate that the TEMPLATES sub-tab is active
#     Then Sum of all sub-tab task counts should be equal to Rejected total task count

#     When User click on Overdue tab
#     Then User validate that the Overdue tab is active
#     And User prints list of tabs under "Overdue" main tab
#     Then Overdue total task count
#     Then Validate Sub-tab External Records is already active
#     When User click on sub tab INTERNAL RECORDS
#     Then User validate that the INTERNAL RECORDS sub-tab is active
#     When User click on sub tab LISTS CONFIGURATION
#     Then User validate that the LISTS CONFIGURATION sub-tab is active
#     When User click on sub tab PRESS RELEASE RECORDS
#     Then User validate that the PRESS RELEASE RECORDS sub-tab is active
#     When User click on sub tab TEMPLATES
#     Then User validate that the TEMPLATES sub-tab is active
#     Then Sum of all sub-tab task counts should be equal to Overdue total task count

#     When User click on Review tab
#     Then User validate that the Review tab is active
#     And User prints list of tabs under "Review" main tab
#     Then Review total task count
#     Then Validate Sub-tab External Records is already active
#     When User click on sub tab INTERNAL RECORDS
#     Then User validate that the INTERNAL RECORDS sub-tab is active
#     Then Sum of all sub-tab task counts should be equal to Review total task count
 
#     Examples:
#       | tenantId | userID              | password       |
#       | 'facctum'| 'facctListusername' | 'facctListPass'|

# @PaginationValidation
# Scenario Outline: Task Page Pagination Validation
#     Given User perform complete login with <tenantId> <userID> <password>
#     When User click on "List Management" card
#     Then User click on "Tasks" button
#     When User click on External Records sub-tab
#     Then User validates Pagination count with page row counts
#     When User changes the pagination count to 10
#     Then pagination count should change to 10
#     And On Each next button click pagination count should increase by rows per page selected
#     When User changes the pagination count to 20
#     Then pagination count should change to 20
#     And On Each next button click pagination count should increase by rows per page selected
#     When User changes the pagination count to 30
#     Then pagination count should change to 30
#     And On Each next button click pagination count should increase by rows per page selected
#     When User changes the pagination count to 40
#     Then pagination count should change to 40
#     And On Each next button click pagination count should increase by rows per page selected
#     When User changes the pagination count to 50
#     Then pagination count should change to 50 
#     And On Each next button click pagination count should increase by rows per page selected
#     When User changes the pagination count to 100
#     Then pagination count should change to 100
#     And On Each next button click pagination count should increase by rows per page selected
#     When User click on External Records sub-tab
#     Then validate reords count in pagination text
#     When User click on Double Arrow Right icon
#     Then User should navigated to last page and validate pagination count
#     And Validated that btnlastPage and btnnextPage are disabled
#     Then On Each back button click pagination count should decrease by rows per page selected
#     When User changes the pagination count to 20
#     Then pagination count should change to 20
#     When User click on Double Arrow Right icon
#     Then On Each back button click pagination count should decrease by rows per page selected
#     When User click on Double Arrow Left icon
#     Then User should navigated to first page
#     And Validated that btnfirstPage and btnpreviousPage are disabled
#     When User click on Double Arrow Right icon
#     Then User should navigated to last page and validate pagination count
#     When User changes the pagination count to 30
#     Then pagination count should change to 30
#     When User click on Double Arrow Right icon
#     Then On Each back button click pagination count should decrease by rows per page selected
#     When User click on Double Arrow Right icon
#     Then User should navigated to last page and validate pagination count
#     When User changes the pagination count to 40
#     Then pagination count should change to 40
#     When User click on Double Arrow Right icon
#     Then On Each back button click pagination count should decrease by rows per page selected
#     When User click on Double Arrow Right icon
#     Then User should navigated to last page and validate pagination count
#     When User changes the pagination count to 50
#     Then pagination count should change to 50
#     When User click on Double Arrow Right icon
#     Then On Each back button click pagination count should decrease by rows per page selected
#     When User click on Double Arrow Right icon
#     Then User should navigated to last page and validate pagination count
#     When User changes the pagination count to 100
#     Then pagination count should change to 100
#     When User click on Double Arrow Right icon
#     Then On Each back button click pagination count should decrease by rows per page selected

#      Examples:
#       | tenantId | userID              | password       |
#       | 'facctum'| 'facctListusername' | 'facctListPass'|
  
# @ExternalRecordsValidation
# Scenario Outline: Task Page External Records Validation
#     Given User perform complete login with <tenantId> <userID> <password>
#     When User click on "List Management" card
#     Then User click on "Tasks" button
#     And user collapses the left panel
#     When User click on External Records sub-tab 
#     And User click on Claimed by filter icon
#     And User applies filters "Claimed" and views complete data
#     And Validate that claimed records contains user name and have lock icon
#     Then user clicks on refresh button 
#     When User click on Claimed by filter icon
#     And User applies filters "Unclaimed" and views complete data
#     Then user clicks on refresh button 
#     When User click on Claimed by filter icon
#     Then User applies filters "Claimed, Claimed by me" and views complete data
#     And user clicks on refresh button 
#     When User click on Claimed by filter icon
#     Then User applies filters "Claimed by me" and views complete data
#     And user clicks on refresh button 
#     When User click on Claimed by filter icon
#     Then  User selects filter checkboxes "Select all"
#     When User changes the pagination count to 20
#     Then pagination count should change to 20
#     When User scrolls to middle then to end of page
#     Then user clicks on refresh button
#     When User Click on Source Filter
#     Then User applies filters "Reconciliation" and views complete data
#     When User Click on Source Filter
#     Then User applies filters "Commercial" and views complete data
#     When User Click on sort by new to old date in submitted column
    
#     Examples:
#       | tenantId | userID              | password       |
#       | 'facctum'| 'facctListusername' | 'facctListPass'|

@InternalRecordsValidation
Scenario Outline: Task Page Internal Records Validation
    Given User perform complete login with <tenantId> <userID> <password>
    When User click on "List Management" card
    Then User click on "Tasks" button  
    And user collapses the left panel
    When User click on sub tab INTERNAL RECORDS
    And User click on Claimed by filter icon
    # And User applies filters "Claimed" and views complete data
    # And Validate that claimed records contains user name and have lock icon
    # Then user clicks on refresh button 
    # When User click on Claimed by filter icon
    And User applies filters "Unclaimed" and views complete data
    When User click on List Name filter icon
    Then User applies filters "IBL notification" and views complete data
    When User click on Action filter icon
    Then User applies filters "Add" and views complete data
    When User click on Type filter icon
    Then User applies filters "Aircraft" and views complete data
#     Then user clicks on refresh button 
#     When User click on Claimed by filter icon
#     Then User applies filters "Claimed by me" and views complete data
#     And user clicks on refresh button 
#     When User click on Claimed by filter icon
#     Then User applies filters "Unclaimed, Claimed by me" and views complete data
#     And user clicks on refresh button 
#     When User click on Claimed by filter icon
#     Then  User selects filter checkboxes "Select all"
#     When User changes the pagination count to 20
#     Then pagination count should change to 20
#     When User scrolls to middle then to end of page
#     Then user clicks on refresh button
  Examples:
        | tenantId | userID              | password       |
        | 'facctum'| 'facctListusername' | 'facctListPass'|

@REGULATORYADVANCEFILTER
Scenario Outline: Regulatory Advance Filter Validation
    Given User perform complete login with <tenantId> <userID> <password>
    And   Navigate to Regulatory List
    And   Select List name
    And   Select Tab
    And   Apply Filter
    And   Check the status

  Examples:
        | tenantId | userID              | password       |
        | 'facctum'| 'facctListusername' | 'facctListPass'|

    # When User click on sub tab LISTS CONFIGURATION
    # And User click on Claimed by filter icon
    # And User applies filters "Claimed" and views complete data
    # And Validate that claimed records contains user name and have lock icon
    # Then user clicks on refresh button 
    # When User click on Claimed by filter icon
    # And User applies filters "Unclaimed" and views complete data
    # Then user clicks on refresh button 
    # When User click on Claimed by filter icon
    # Then User applies filters "Claimed by me" and views complete data
    # And user clicks on refresh button 
    # When User click on Claimed by filter icon
    # Then User applies filters "Unclaimed, Claimed by me" and views complete data
    # And user clicks on refresh button 
    # When User click on Claimed by filter icon
    # Then  User selects filter checkboxes "Select all"
    # When User changes the pagination count to 20
    # Then pagination count should change to 20
    # When User scrolls to middle then to end of page
    # Then user clicks on refresh button

    # When User click on sub tab PRESS RELEASE RECORDS
    # And User click on Claimed by filter icon
    # And User applies filters "Claimed" and views complete data
    # And Validate that claimed records contains user name and have lock icon
    # Then user clicks on refresh button 
    # When User click on Claimed by filter icon
    # And User applies filters "Unclaimed" and views complete data
    # Then user clicks on refresh button 
    # When User click on Claimed by filter icon
    # Then User applies filters "Claimed by me" and views complete data
    # And user clicks on refresh button 
    # When User click on Claimed by filter icon
    # Then User applies filters "Unclaimed, Claimed by me" and views complete data
    # And user clicks on refresh button 
    # When User click on Claimed by filter icon
    # Then  User selects filter checkboxes "Select all"
    # When User changes the pagination count to 20
    # Then pagination count should change to 20
    # When User scrolls to middle then to end of page
    # Then user clicks on refresh button

    # When User click on sub tab TEMPLATES
    # And User click on Claimed by filter icon
    # And User applies filters "Claimed" and views complete data
    # And Validate that claimed records contains user name and have lock icon
    # Then user clicks on refresh button 
    # When User click on Claimed by filter icon
    # And User applies filters "Unclaimed" and views complete data
    # Then user clicks on refresh button 
    # When User click on Claimed by filter icon
    # Then User applies filters "Claimed by me" and views complete data
    # And user clicks on refresh button 
    # When User click on Claimed by filter icon
    # Then User applies filters "Unclaimed, Claimed by me" and views complete data
    # And user clicks on refresh button 
    # When User click on Claimed by filter icon
    # Then  User selects filter checkboxes "Select all"
    # When User scrolls to middle then to end of page
    # Then user clicks on refresh button


    # Then checked on Select all checkbox
    # Then User Validated the results
    # When User click on Claimed by filter icon
    # Then checked on Select all checkbox
    # When User click on Claimed by filter icon
    # And Checked on Claimed checkbox
    # And Validate that claimed records contains user name and have lock icon
    # When User click on claimed all record checkbox
    # Then Validate alert text and close alert
    # When User validate the submitted by column is sorted by old to new date
    # Then user clicks on refresh button
    # When User Click on sort by new to old date in submitted column
    # When User Click on Source Filter
    # Then User Selects a Reconciliation source and validated the results
    # When User Click on Source Filter
    # Then User Selects a Commercial source and validated the results
    # When User select a random record checkbox

    # Then user clicks on refresh button
    # When User click on Claimed by filter icon
    # And Checked on Unclaimed checkbox
    # When User select a random external record checkbox
    # Then User click on claim button and validates the results
    
    # Validating INTERNAL Records tab
    # When User click on sub tab INTERNAL RECORDS
    # Then User click on Claimed by filter icon
    # And Checked on Unclaimed checkbox
    # Then User select a random record checkbox
    # And User click on claim button and validates the results
    # When user claim and unclaim record from same page
    # When User Claim and reject record and validate the results
    # When User Claim and Accept record and validate the results

    

    
    

    

    # When User click on Claimed by filter icon
    # Then Checked on Claimed checkbox
    # When Checked on Unclaimed checkbox
    # And User validated the unclaimed records count is less than or equal to the total records count
    # And Validate that unclaimed records doesn't contains any user name
    
    

    
    
    
    # When User click on the "EXTERNAL RECORDS" sub-tab
    # Then the "EXTERNAL RECORDS" sub-tab should be active
    # When User click on the "INTERNAL RECORDS" sub-tab
    # Then the "INTERNAL RECORDS" sub-tab should be active
    # When User click on the "LISTS CONFIGURATION" sub-tab
    # Then the "LISTS CONFIGURATION" sub-tab should be active
    # When User click on the "PRESS RELEASE RECORDS" sub-tab
    # Then the "PRESS RELEASE RECORDS" sub-tab should be active
    # When User click on the "TEMPLATES" sub-tab
    # Then the "TEMPLATES" sub-tab should be active

    


    # Examples:
    #   | tenantId | userID              | password       |
    #   | 'facctum'| 'facctListusername' | 'facctListPass'|
