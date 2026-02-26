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
@tag
Feature: Title of your feature
I want to use this template for my feature file

  
@loginFacctView
  Scenario Outline: LoginToFacctView
    Given I Open url "facctView"
    And I validate Login page text "A cloud-first screening capability that is agile, fast"
    Given I click to facctView login button
    Given I enter tenant name <tenantId>
    And I click to continue button
    And I enter facctView login id <userID>
    And I enter facctView login password <password>
    And I click to next button
    Then I validate welcome page text "Welcome to FacctView"
    Then user gets first list
    |SEARCH|
    |BATCH|
    |DEVELOPER DOCS|
    And listone count should be 3
    
Examples:
| userID  | password | tenantId  |
|"facctViewusername"|"facctViewPass"|"datavium"|
