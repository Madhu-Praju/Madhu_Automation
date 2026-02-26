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
@tag1
Feature: Title of your feature
  I want to use this template for my feature file

@SingleSearchFacctView1
  Scenario Outline: LoginToFacctView
   # Given I Open given url <WebURL>
   # Given I Open url "facctView"
  # Then User get name from page
   Then User get all the details from page
   #Then User get family name from page
   #Then User get gender from page
   #Then User get Dob from page
#		Then User get pob from page
#		Then User get nation from page
#		Then User get language from page
#		Then User get charges from page
#	Then User get charges from page
#Then User downloads all the link from CBI
   
   
   
   
    
Examples:
| WebURL  | 
|"https://www.interpol.int/How-we-work/Notices/Red-Notices/View-Red-Notices#2023-61645"|
