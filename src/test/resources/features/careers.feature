@careers
  Feature: Careers scenarios

    @careers1
    Scenario: Recruiter creates position
      Given I open "careers" page
      And I login as "recruiter"
      Then I verify "recruiter" login
      When I create new position
      And I verify position created
