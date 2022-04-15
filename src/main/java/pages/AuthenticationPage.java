package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Locale;

public class AuthenticationPage extends BasePage{
    WebDriver driver;
    Faker faker = new Faker(new Locale("en-ZA"));

    //Locators
    @FindBy(id = "email_create")
    WebElement input_CreateAccountEmailAddress;
    @FindBy(id = "email")
    WebElement input_ExistingAccountEmailAddress;
    @FindBy(id = "passwd")
    WebElement input_ExistingAccountPassword;
    @FindBy(id = "customer_firstname")
    WebElement input_PersonalInformationCustomerFirstname;
    @FindBy(id = "customer_lastname")
    WebElement input_PersonalInformationCustomerLastname;
    @FindBy(id = "firstname")
    WebElement input_AddressInformationCustomerFirstname;
    @FindBy(id = "lastname")
    WebElement input_AddressInformationCustomerLastname;
    @FindBy(id = "address1")
    WebElement input_AddressLine1;
    @FindBy(id = "address2")
    WebElement input_AddressLine2;
    @FindBy(id = "company")
    WebElement input_Company;
    @FindBy(id = "city")
    WebElement input_City;
    @FindBy(id = "postcode")
    WebElement input_PostCode;
    @FindBy(id = "other")
    WebElement input_AdditionalInformation;
    @FindBy(id = "phone")
    WebElement input_HomePhoneNumber;
    @FindBy(id = "phone_mobile")
    WebElement input_MobilePhoneNumber;
    @FindBy(id = "alias")
    WebElement input_Alias;
    @FindBy(id = "registerAccount")
    WebElement input_RegisterAccount;
    @FindBy(id = "welcomeMessage")
    WebElement alert_WelcomeMessage;

    @FindBy(id = "id_state")
    WebElement select_State;
    @FindBy(id = "id_country")
    WebElement select_Country;

    @FindBy(id = "SubmitCreate")
    WebElement button_CreateAnAccount;
    @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
    WebElement button_ForgotPassword;
    @FindBy(id = "SubmitLogin")
    WebElement button_SignIn;
    @FindBy(id = "submitAccount")
    WebElement button_RegisterNewAccount;

    @FindBy(id = "create_account_error")
    WebElement alert_RegisterNewAccount;

    //Constructor
    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    //Page actions
    public void goToLoginPage(){
        driver.get("http://automationpractice.com/index.php?controller=authentication");
    }

    public void inputCreateAccountEmailAddress(String email){
        input_CreateAccountEmailAddress.sendKeys(email);
    }

    public void clickCreateAnAccountButton(){
        button_CreateAnAccount.click();
    }

    public void verifyCustomerIsOnAuthPage() {
        Assert.assertTrue(driver.getTitle().equalsIgnoreCase("http://automationpractice.com/index.php?controller=authentication#account-creation"));
    }

    public void captureBasicPersonalInformation() {
        input_PersonalInformationCustomerFirstname.sendKeys(faker.name().firstName());
        input_PersonalInformationCustomerLastname.sendKeys(faker.name().lastName());
        input_ExistingAccountPassword.sendKeys(faker.superhero().name());
    }

    public void captureBasicAddressInformation() {
        input_AddressLine1.sendKeys(faker.address().fullAddress());
        input_City.sendKeys(faker.address().city());
        new Select(select_State).selectByVisibleText(faker.address().state());
        input_PostCode.sendKeys(faker.address().zipCode());
        input_MobilePhoneNumber.sendKeys(faker.phoneNumber().cellPhone());
    }

    public void clickRegisterNewAccountButton(){
        button_RegisterNewAccount.click();
    }

    public void verifyNewCustomerAccountIsCreated() {
        Assert.assertTrue(input_RegisterAccount.isDisplayed(), "New account is not created.");
    }

    public void validateNewCustomerEmailAddress(String response) {
        if(response.equalsIgnoreCase("ok")){
            verifyCustomerIsOnAuthPage();
        }else if(response.equalsIgnoreCase("error")){
            Assert.assertTrue(alert_RegisterNewAccount.isDisplayed());
        }
    }

    public void validateExistingCustomerEmailAddress(String response) {
        if(response.equalsIgnoreCase("ok")){
            Assert.assertTrue(alert_WelcomeMessage.isDisplayed());
        }else if(response.equalsIgnoreCase("error")){
            Assert.assertFalse(alert_WelcomeMessage.isDisplayed());
        }
    }

    public void inputExistingAccountEmailAddress(String email) {
        input_ExistingAccountEmailAddress.sendKeys(email);
    }

    public void clickExistingAccountSignInButton() {
        button_SignIn.click();
    }

    public void captureLoginDetails(String username, String password) {
        input_ExistingAccountEmailAddress.sendKeys(username);
        input_ExistingAccountPassword.sendKeys(password);
    }
}
