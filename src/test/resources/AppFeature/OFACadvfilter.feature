@OFACADVANCEFILTER
Feature: Regulatory OFAC Advance Filter
  I want to use this template for my feature file

  @ApplyingofOFACFilter
  Scenario: Applying of OFAC Filter
    Given Facctlist Login 1
    And   Navigate to Regulatory List 1
    And   Select List name 1
    And   Apply Filter in all tabs 1 
    And   Check the status 1

  @OFACFilterDBValidation
  Scenario: Validate OFAC Filter count with MongoDB
    Given Facctlist Login 1
    And   Navigate to Regulatory List 1
    And   Select List name 1
    And   Apply Address Country filter with "Cuba"
    And   Capture the filtered record count from UI
    And   Fetch the record count from MongoDB for address country "Cuba"
    Then  Validate UI count matches MongoDB count