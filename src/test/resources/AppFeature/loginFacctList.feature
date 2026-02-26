#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
 @loginfacctlist
Feature: Login to FactList and add passlist records
  I want to use this template for my feature file
  @loginfacctlist
  Scenario Outline: Login to FactList and add facctlist record
    Given I Open url "facctList"
    And User validate Welcome page elements
    Given User click on login button on Welcome page
    And User validates welcome texts
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
    # And page title should be "Dashboard"
    # And user enters the login id
    # And user enters the password
    # And user presses continue key to Login to Dashboard
    # Then user gets the title of the page
    
    #Given User click on watchlistSources link
    #Given User click on passlist link
    #Then Navigate to the main window
    #And Click and create the passlist
    #|passlist_autom_internal|
    #|9|
    #And validate that the passlist is created
    #And Search passlist and get status
    #|passlist_autom_internal|
    #And Navigate based on status
    #Given User click on watchlistSources link
    #Given User click on passlist link
    #Then Navigate to the main window
    #Then user clicks on passlist link to add records <passlistname>
    #Then user clicks to add records
    #Then user add form values <Name> <Supress_tag> and <Date>
    #Then user enters non manddatory field values <address> <cty> <suptag> <suorig> <suunit> <txtarea>
    #Then user clicks save as draft button
    #And user clicks to submit button
    # And user navigate to task screen
    # Then user validate task screen and clicks passlist record button
    # Then user verify status and approves the passlist record <Name> <passlistname>
    
  
  Examples:
    | tenantId |passlistname|Name|Supress_tag|Date|address|cty|suptag|suorig|suunit|txtarea|userID|password|
    |'facctum'|'passlist_autom_internal'|"Christopher"|"1288221888"|"03/07/2024"|'Presidential William, Damascus'|'Damascus'|'122,331'|'Piya12312312'|'Bhara999900'|'Houmam AbdelKhalik AbdelGhafour uncleee'|'facctListusername'|'facctListPass'|
  #  |'datavium'|'passlist_autom_internal'|"James Matthew"|"1288332999"|"03/08/2024"|'Sophia garden'|'Damascus'|'123,332'|'Keta12312312'|'Bhara999911'|'Houmam AbdelKhalik AbdelGhafour jiyaaa'|
    # |'datavium'|'passlist_autom_internal'|"Anthony"|"1284438000"|"03/09/2024"|'Charlotte Palace cityy, rowhouse'|'Damascus'|'124,333'|'Piya12312312'|'Bhara999922'|'Houmam AbdelKhalik AbdelGhafour ganaaa'|
		#|'datavium'|'passlist_autom_internal'|"Andrew Kumarannnnn"|"1212133411"|"03/09/2025"|'Isabella light villah, Saudi'|'OMAN'|'IDL,HGZ'|'Reem22222222'|'REEM322'|'Houmamaaa AbdelKhalik AbdelGhafour jiyjayega ganaaa going back dddd'|
	# |'datavium'|'passlist_autom_internal'|"Kenneth George"|"1214445111"|"03/04/2025"|'QEvelyn villah, UK'|'UK'|'CAR,TER'|'CAAR22222222'|'CART322'|'AbdelGhafour jiyjayega ganaaa going back dddd CART'|

  
  

 
