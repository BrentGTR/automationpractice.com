package stepdefinitions;

import common.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class HomePageSteps {
    HomePage homePage = new HomePage(WebDriverFactory.getDriver());

    @Given("Customer is on the home page")
    public void customerIsOnTheHomePage() {
        homePage.goToHomePage();
    }

    @When("Customer searches for {string}")
    public void customerSearchesFor(String product) {
        homePage.searchesFor(product);
    }

    @When("Customer clicks on the List button")
    public void customerClicksOnTheListButton() {
        homePage.clickOnListButton();
    }

    @Then("search results page should display the list of {string}")
    public void searchPageShouldDisplayTheListOf(String product) {
        homePage.assertProductIsDisplayed(product);
    }

    @Then("no results warning should be displayed for {string}")
    public void noResultsWarningShouldBeDisplayedFor(String product) {
        homePage.assertNoProductsAreDisplayedFor(product);
    }

    @Then("the grid view is selected")
    public void theGridViewIsSelected() {
        homePage.gridViewIsSelected();
    }

    @Then("the list view is selected")
    public void theListViewIsSelected() {
        homePage.listViewIsSelected();
    }

    @When("customer clicks on sign in button")
    public void customerClicksOnSignInButton() {
        homePage.clicksOnSignInButton();
    }
}
