Feature: Login functionality

  @Fast @Smoke
  Scenario: When an already registered customer uses correct user credentials then the customer is logged into the online store
    Given Customer is on the home page
    When customer clicks on sign in button
    And customer captures the "validemail@yop.com" and "Password1*"
    And existing customer clicks on sign in button
    Then existing customer sees a response of "ok"

  Scenario Outline: When a customer captures an email address then the appropriate response should be displayed
    Given Customer is on the authentication page
    When existing customer captures email of "<email>" address
    And existing customer clicks on sign in button
    Then existing customer sees a response of "<response>"
    Examples:
      | email                         | response |
#      Valid email addresses
      | email@domain.com              | ok       |
      | firstname.lastname@domain.com | ok       |
      | email@subdomain.domain.com    | ok       |
      | firstname+lastname@domain.com | ok       |
      | email@123.123.123.123         | ok       |
      | email@[123.123.123.123]       | ok       |
      | 1234567890@domain.com         | ok       |
      | email@domain.co.jp            | ok       |
      | nathan@学生优惠.com            | ok       |

#      Invalid email addresses
      | email.domain.com              | error    |
      | @domain.com                   | error    |
      | plainaddress                  | error    |
      | #@%^%#$@#$@#.com              | error    |
      | Joe Smith <email@domain.com>  | error    |
      | email@domain@domain.com       | error    |
      | email.@domain.com             | error    |
      | あいうえお@domain.com          | error    |


#  @ISS-987: 406 response when registering new customer
  @E2E @Failing @Skip
  Scenario: When a new customer attempts to register with a valid email then the customer is successfully registered
    Given Customer is on the authentication page
    When customer captures email of "validemail@yop.com" address to create an account
    And customer clicks on the Create an account button
    Then customer is on the Authentication page
    When customer captures information in the Your Personal Information section
    And customer captures information in the Your Address section
    And customer clicks on the Register button
    Then customer account is created

  Scenario Outline: When a new customer captures an email address then the appropriate response should be displayed
    Given Customer is on the authentication page
    When customer captures email of "<email>" address to create an account
    And customer clicks on the Create an account button
    Then customer sees a create account response of "<response>"
    Examples:
      | email                         | response |
#      Valid email addresses
      | email@domain.com              | ok       |
      | firstname.lastname@domain.com | ok       |
      | email@subdomain.domain.com    | ok       |
      | firstname+lastname@domain.com | ok       |
      | email@123.123.123.123         | ok       |
      | email@[123.123.123.123]       | ok       |
      | 1234567890@domain.com         | ok       |
      | email@domain.co.jp            | ok       |
      | nathan@学生优惠.com            | ok       |

#      Invalid email addresses
      | email.domain.com              | error    |
      | @domain.com                   | error    |
      | plainaddress                  | error    |
      | #@%^%#$@#$@#.com              | error    |
      | Joe Smith <email@domain.com>  | error    |
      | email@domain@domain.com       | error    |
      | email.@domain.com             | error    |
      | あいうえお@domain.com          | error    |