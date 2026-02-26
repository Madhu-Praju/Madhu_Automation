@RecordApproval
Feature: Approval and Rejection of the record from task page
  I want to use this template for my feature file

  @ApprovaloftheRecord
  Scenario: Approval of the Record
    Given  Login facctlist
    And    Navigate to Task Page
    And    Navigate to Internal Records
    And    Approve the Record
