@GlobalSearch
Feature: Global Search Functionality
  
  User want to search for records using global search
  So that User can find specific data efficiently

  Background: Login to Application
    Given I Open url "facctList"
    And User validate Welcome page elements
    Given User click on login button on Welcome page
    And User validates welcome texts

    @GlobalSearchListRecordID
    Scenario Outline: Global search by RecordID Name and IDValue
    
        # Reusable Login - Single step replaces all login complexity
        Given I perform complete login to home page with <tenantId> <userID> <password>
        
        # Core Global Search Functionality
        When User click on "List Management" card
        And navigate to the global search page
        And user collapses the left panel
        And should validate the URL contains "search"
        Then Validate search button is disabled
        When User selects a watchlist option "Internal list"
        When User selects list "IBL08-29" and search for "Rec0000003"

        Examples:
          | tenantId | userID              | password       |
          | 'facctum'| 'facctListusername' | 'facctListPass'|

    @GlobalSearchBulkRecordIDSearch
    Scenario Outline: Global search for Bulk Record ID (Minimal Steps)
    # Even simpler - just basic login without home page validation
        Given User perform complete login with <tenantId> <userID> <password>
        
        # Navigate to Global Search
        When User click on "List Management" card
        And navigate to the global search page
        And user collapses the left panel
        And should validate the URL contains "search"
        Then Validate search button is disabled
        When User selects a search by option "Record ID"
        When User selects a watchlist option "External list"
        When User perform search for each record in Excel data with Watchlist validation

        Examples:
          | tenantId | userID              | password       |
          | 'facctum'| 'facctListusername' | 'facctListPass'|

        @GlobalSearchBulkNameSearch
        Scenario Outline: Global search for Bulk Name (Minimal Steps)
        # Even simpler - just basic login without home page validation
        Given User perform complete login with <tenantId> <userID> <password>
        
       # Navigate to Global Search
        When User click on "List Management" card
        And navigate to the global search page
        And user collapses the left panel
        And should validate the URL contains "search"
        Then Validate search button is disabled
        When User selects a watchlist option "External list"
        When User Selects search by option "Name"
        Then User perform search for each Name in Excel data with Watchlist validation

        Examples:
          | tenantId | userID              | password       |
          | 'facctum'| 'facctListusername' | 'facctListPass'|

     @GlobalSearchBulkIDValueSearch
        Scenario Outline: Global search for Bulk ID Value (Minimal Steps)
        # Even simpler - just basic login without home page validation
        Given User perform complete login with <tenantId> <userID> <password>
        
       # Navigate to Global Search
        When User click on "List Management" card
        And navigate to the global search page
        And user collapses the left panel
        And should validate the URL contains "search"
        Then Validate search button is disabled
        When User selects a watchlist option "External list"
        When User Selects search by option "ID value"
        When User perform search for each ID Value in Excel data with Watchlist validation

        Examples:
          | tenantId | userID              | password       |
          | 'facctum'| 'facctListusername' | 'facctListPass'|
      
      @GlobalSearchForMixedList
       Scenario Outline: Global search for Mixed list (Minimal Steps)
        # Even simpler - just basic login without home page validation
        Given User perform complete login with <tenantId> <userID> <password>
        
       # Navigate to Global Search
        When User click on "List Management" card
        And navigate to the global search page
        And user collapses the left panel
        And should validate the URL contains "search"
        Then Validate search button is disabled
        When User selects a watchlist option "Internal list"
        When User selects multiple list names "IBL08-29,IBL04-08" and search for "Rec00007" with search type "Record ID"

         Examples:
          | tenantId | userID              | password       |
          | 'facctum'| 'facctListusername' | 'facctListPass'|

      @GlobalSearchForSingleListTAttrinute
       Scenario Outline: Global search for Single List Attribute (Minimal Steps)
        # Even simpler - just basic login without home page validation
        Given User perform complete login with <tenantId> <userID> <password>
        
       # Navigate to Global Search
        When User click on "List Management" card
        And navigate to the global search page
        And user collapses the left panel
        And should validate the URL contains "search"
        Then Validate search button is disabled
        When User selects list "IBL04-08" and search from Excel
        

         Examples:
          | tenantId | userID              | password       |
          | 'facctum'| 'facctListusername' | 'facctListPass'|

    # @GlobalSearchFullValidation
    # Scenario Outline: Global search with Complete Login Validation
    #     # Complete login with all validations (tenant, user ID, forgot password link, etc.)
    #     Given I perform complete login with validation using <tenantId> <userID> <password>
        
    #     # Navigate to Global Search
    #     When User click on "List Management" card
    #     And navigate to the global search page
    #     And user collapses the left panel
    #     And should validate the URL contains "search"
    #     Then Validate search button is disabled

    #     Examples:
    #       | tenantId | userID              | password       |
    #       | 'facctum'| 'facctListusername' | 'facctListPass'|