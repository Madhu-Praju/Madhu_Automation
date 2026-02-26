
#Sample Feature Definition Template
@tag
Feature: Title of your feature
  I want to use this template for my feature file
  
 
 @SingleSearchFacctView
  Scenario Outline: Validate individual search result for number of name and corresponding score as per expected result from excel
   
    Given I Open url "facctView"
    And I validate Login page text "A cloud-first screening capability that is agile, fast"
    Given I click to facctView login button
    Given I enter tenant name <tenantId>
    And I click to continue button
    And I enter facctView login id <userID>
    And I enter facctView login password <password>
    And I click to next button
    Then I validate welcome page text "Welcome to FacctView"
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
  	Then User validate result is displayed from excel <Name>
    Then User verifies all the result from excel for search <Name>
    Then User download JSON file
    #Then User verifies popup alert "Downloaded"

    
Examples:
| userID  | password | tenantId  |Name|
|"facctViewusername"|"facctViewPass"|"datavium"|"Osama Al"|
|"facctViewusername"|"facctViewPass"|"datavium"|"Ibrahim"|
