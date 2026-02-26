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
@facctlistpageoption
Feature: facctlist page option
  I want to use this template for my feature file

Scenario Outline: Login to FactList
		Given I Open url "facctList"
  	Given I click on URL
  	Given I verify login page by welcome text
    Given I click on login button
    Given user enters the organization name <Orgname>
    And I press continue key
    When Users enters the login page
    And user enters the login id
    And user enters the password
    And I press continue key to Login to Dashboard
    Then user gets the title of the page
    And page title should be "Welcome to"
  
  Examples:
    | Orgname |
    |'facctum'|
    
    
    
  #Scenario: To validate options avilable on facctlist home page
    #Given I want to write a step with precondition
    #And some other precondition
    #When I complete action
    #And some other action
    #And yet another action
    #Then I validate the outcomes
    #And check more outcomes

  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
