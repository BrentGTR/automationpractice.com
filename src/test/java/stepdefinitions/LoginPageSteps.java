package stepdefinitions;

import common.WebDriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AuthenticationPage;

public class LoginPageSteps {
    AuthenticationPage authenticationPage = new AuthenticationPage(WebDriverFactory.getDriver());

    @Given("Customer is on the authentication page")
    public void customerIsOnTheLoginPage(){
        authenticationPage.goToLoginPage();
    }

    @When("^customer captures email of \"([^\"]*)\" address to create an account$")
    public void inputCreateAccountEmailAddress(String email){
        authenticationPage.inputCreateAccountEmailAddress(email);
    }

    @When("customer clicks on the Create an account button")
    public void customerClicksOnTheCreateAnAccountButton() {
        authenticationPage.clickCreateAnAccountButton();
    }

    @Then("customer is on the Authentication page")
    public void customerIsOnTheAuthenticationPage() {
        authenticationPage.verifyCustomerIsOnAuthPage();
    }

    @When("customer captures information in the Your Personal Information section")
    public void customerCapturesInformationInTheYourPersonalInformationSection() {
        authenticationPage.captureBasicPersonalInformation();
    }

    @When("customer captures information in the Your Address section")
    public void customerCapturesInformationInTheYourAddressSection() {
        authenticationPage.captureBasicAddressInformation();
    }

    @When("customer clicks on the Register button")
    public void clicksOnTheRegisterButton() {
        authenticationPage.clickRegisterNewAccountButton();
    }

    @Then("customer account is created")
    public void customerAccountIsCreated() {
        authenticationPage.verifyNewCustomerAccountIsCreated();
    }

    @Then("customer sees a create account response of {string}")
    public void customerSeesAResponseOf(String response) {
        authenticationPage.validateNewCustomerEmailAddress(response);
    }

    @When("existing customer captures email of {string} address")
    public void existingCustomerCapturesEmailOfAddress(String email) {
        authenticationPage.inputExistingAccountEmailAddress(email);
    }

    @When("existing customer clicks on sign in button")
    public void existingCustomerClicksOnSignInButton() {
        authenticationPage.clickExistingAccountSignInButton();
    }

    @Then("existing customer sees a response of {string}")
    public void existingCustomerSeesAResponseOf(String response) {
        authenticationPage.validateExistingCustomerEmailAddress(response);
    }

    @And("customer captures the {string} and {string}")
    public void customerCapturesTheAnd(String username, String password) {
        authenticationPage.captureLoginDetails(username, password);
    }
}
