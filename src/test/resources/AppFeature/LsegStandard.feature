@LSEGSTANDARD
Feature: Lseg Standard
  I want to use this template for my feature file

  @LsegStandardSubmission
  Scenario: Commercial List Creation
    Given Login to the Facctlist
    And   Navigate to Commercial List
    And   Creation of Lseg Standard

  @TaskpageApproval
  Scenario: Commercial List Approval
    Given Login to Facctlist
    And   Navigate to Task Page
    And   Approval of Commercial List
