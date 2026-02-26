#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tagforSinglesearch
Feature: Single search feauter for facctview
  I want to use this template for my feature file
  
Background:

	 Given user has already logged into account
	|facctView|datavium|facctViewusername|facctViewPass|A cloud-first screening capability that is agile, fast|Welcome to FacctView|
 
 
 @SingleSearchname
  Scenario Outline: Validate search record against expected record for single value

    Given user click on arrow list
    Then user gets entity list
    |Any|
    |Individual|
    |Entity|
    And listone count should be 3
    Given User selects Individual from list
    Given user enter entity name for search <Name>
    Given User click button for more field
    #Given User enters Entity or National ID <EntityIDNatID>
    #Given User enters DOB or RegID <DateofBirthReg>
    And User click search button
    Then User validate result is displayed <Result>
    Then user verifies the search result against excepted result <MatchName> <MatchStartRange> <MatchendRange>
    Then User download JSON file
  #  Then User verifies popup alert "Downloaded"

    
Examples:
|Name|DateofBirthReg|EntityIDNatID|Result |MatchName|MatchStartRange|MatchendRange|
|"Osama Al"|"DateofBirth"|"NatID"|"9"|"osama al kuni ibrahim"|"85"|"95"|
|"Osama Al"|"DateofBirth"|"NatID"|"9"|"Osama Al Kuni Ibrahim"|"85"|"95"|



