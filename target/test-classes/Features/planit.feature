#Author: Harish Kumar (harish1150@gmail.com)
#Description: Framework to test Demo Planit website
#Date: 28 Oct 2020
@AllTestCases
Feature: Planit Technical Assessment

  @TestCase1
  Scenario: Validate the error messages on contact page
    Given the user launch the homepage url and navigate to contact page
    When the user click on submit button without entering details
    Then the error messages are displayed
    When the user enters the mandatory fields
    Then the error messages should not be displayed

  @TestCase2
  Scenario: Validate the success message after valid submission
    Given the user launch the homepage url and navigate to contact page
    When the user enters the mandatory fields
    And click on the submit button
    Then the success message is displayed to the user

  @TestCase3
  Scenario: Validate the invalid entry error messages on contact page
    Given the user launch the homepage url and navigate to contact page
    When the user enters invalid data on inout fields
    Then the error messsages are displayed to the user

  @TestCase3
  Scenario: Validate the shopping cart page
    Given the user launch the homepage url and navigate to shop page
    When the user adds "Funny Cow" item multiple times to cart
    Then verify the number of items on cart
    When the user adds "Funny Bunny" item to cart
    Then verify the number of items on cart
    When the user clicks on cart menu
    Then the items are present in the shopping cart   
