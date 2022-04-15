Feature: Search functionality
  Background:
    Given Customer is on the home page

    @Skip @End2End @Regression
  Scenario Outline: When a customer searches for a product then all the products related to the input given should be displayed
    When Customer searches for "<search criteria>"
    Then search results page should display the list of "<search criteria>"
    And the grid view is selected
    When Customer clicks on the List button
    Then the list view is selected
    Examples:
      | search criteria |
      | Dress           |
      | T-shirt         |
      | Blouse          |
      | Printed         |

      @Negative
  Scenario Outline: When a customer searches for a product that does not exist then no products should be displayed
    When Customer searches for "<search criteria>"
    Then no results warning should be displayed for "<search criteria>"
    Examples:
      | search criteria                           |
      | Hamster wheel                             |
      | 2147483647                                |
      | Lorem ipsum dolor sit amet, Римский император Константин I Великий, 北京位於華北平原的西北边缘    |
      | <script>alert('Executing JS')</script>    |
      | "-prompt()-"                              |
      | 👩                                        |