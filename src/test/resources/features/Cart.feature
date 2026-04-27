Feature: Carts API Automation



Scenario Outline: Validate POST Carts API
  Given I read carts test data "<TestCaseID>"
  And I validate carts precondition
  When I perform carts API request
  Then I validate carts expected result

Examples:
  | TestCaseID     |
  | TC_Carts_01_01 |
  | TC_Carts_01_02 |
  | TC_Carts_01_03 |
  | TC_Carts_01_04 |




Scenario Outline: Validate GET Carts APIs
  Given I set carts request "<method>" "<endpoint>"
  When I send carts request
  Then I validate carts status "<status>"

Examples:
  | method | endpoint     | status |
  | GET    | /carts       | 200    |
  | GET    | /carts/1     | 200    |
  | GET    | /carts/99999 | 404    |
  | GET    | /carts?limit=-5  | 404    |




Scenario: Validate Carts Response Structure
  Given I set carts request "GET" "/carts"
  When I send carts request
  Then Response body should contain carts array
  And Each cart should have "id", "products", "total", "userId"




Scenario: Update Cart using Data Table
  Given I set carts request "PUT" "/carts/1"
  When I send carts PUT request with body:
    | products                | total | userId |
    | [{"id":1,"quantity":3}] | 500   | 5      |
  Then I validate carts status "200"




Scenario: Update Cart with Invalid Data
  Given I set carts request "PUT" "/carts/1"
  When I send carts PUT request with body:
    | quantity |
    | abc      |
  Then I validate carts status "400"



Scenario Outline: Delete Cart API
  Given I set carts request "DELETE" "/carts/<id>"
  When I send carts request
  Then I validate carts status "<status>"

Examples:
  | id    | status |
  | 1     | 200    |
  | 2     | 200    |
  | 999   | 404    |
  | abc   | 404    |




Scenario: Validate Delete Cart Response Fields
  Given I set carts request "DELETE" "/carts/1"
  When I send carts request
  Then Carts response should contain "isDeleted"
  And Carts response should contain "deletedOn"