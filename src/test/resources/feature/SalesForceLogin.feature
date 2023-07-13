Feature: Test login functionality

  Background: 
   Given user open Salesforce application
   
   Scenario Outline: Check Error Message is displayed when password field is empty
     When user is on "LoginPage"
     When user enters valid username
     And user clicks on login button
    Then user verify error message1
    
   Scenario Outline: Check login is successful with valid credentials  
     When user is on "LoginPage"
     When user enters valid username and valid password
     And user clicks on login button
     When user is on "HomePage"
     Then verify Home Page Title 
    
    
    Scenario Outline: Successful Login after remember me checked and user logged out from HomePage
    When user is on "LoginPage"
    When user enters valid username and valid password
    When click remember me checkbox
    And user clicks on login button
    When user is on "HomePage"
    And user click logout
    And User is on "LoginPage"
    Then username is displayed in the username field
    
    Scenario Outline: Check Forgot password  functionality without entering user credentials
     When user is on "LoginPage"
    When user clicks the Forgotpassword link
    When user is navigated to "ForgotPasswordPage"
    When User is on "CheckYourEmailPage"
    And User clicks returnToLogin 
    Then Verify user is on LoginPage by verifying the LoginPage title
    
    
    Scenario Outline: Check login is unsuccessful with invalid credentials
    When user is on "LoginPage"
    When User enters invalid username and invalid password
      And user clicks on login button
    Then verfiy error message 
    
    
    
    
    
    
    
    
    
     
    
    