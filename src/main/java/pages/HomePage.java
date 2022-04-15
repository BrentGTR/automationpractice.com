package pages;

import common.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePage {
    WebDriver driver = WebDriverFactory.getDriver();

    //Locators
    @FindBy(name = "submit_search")
    WebElement button_Search;

    @FindBy(xpath = "//ul[contains(@id,'homeslider')]")
    WebElement div_HomeSlider;
    @FindBy(xpath = "//p[contains(@class,'alert-warning')][contains(text(),'No results were found for your search')]")
    WebElement warning_NoProductsFound;
    @FindBy(xpath = "//div[contains(@class,'product-container')]")
    List<WebElement> container_Products;
    @FindBy(xpath = "//span[contains(@class,'lighter')]")
    WebElement text_SearchCriteria;
    @FindBy(xpath = "//span[contains(text(),'Add to cart')]")
    WebElement button_AddToCard;
    @FindBy(xpath = "//a[contains(@class,'login')][contains(text(),'Sign in')]")
    WebElement button_SignIn;

    @FindBy(id = "search_query_top")
    WebElement input_Search;
    @FindBy(id = "grid")
    WebElement icon_Grid;
    @FindBy(id = "list")
    WebElement icon_List;

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Page actions
    public void goToHomePage(){
        driver.get("http://automationpractice.com/index.php");
    }

    public void assertHomeSlider(){
        Assert.assertTrue(div_HomeSlider.isDisplayed(), "Homeslider is not displayed.");
    }

    public void searchesFor(String product) {
        input_Search.sendKeys(product);
        button_Search.click();
    }

    public void assertProductIsDisplayed(String product) {
        Assert.assertTrue(text_SearchCriteria.getText().toLowerCase().contains(product.toLowerCase()));
        Assert.assertTrue(container_Products.size() > 0, "No products are displayed.");
    }

    public void assertNoProductsAreDisplayedFor(String product) {
        Assert.assertTrue(warning_NoProductsFound.isDisplayed());
        Assert.assertEquals(container_Products.size(), 0, "Products should not be displayed.");
    }

    public void gridViewIsSelected() {
        Assert.assertTrue(icon_List.getAttribute("class").contains("selected"));
        Assert.assertFalse(button_AddToCard.isDisplayed());
    }

    public void listViewIsSelected() {
        Assert.assertTrue(icon_Grid.getAttribute("class").contains("selected"));
        Assert.assertTrue(button_AddToCard.isDisplayed());
    }

    public void clickOnListButton() {
        icon_List.click();
    }

    public void clicksOnSignInButton() {
        button_SignIn.click();
    }
}
