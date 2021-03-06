package common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class WebDriverFactory {
    static Properties properties = null;
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initializeDriver() {
        properties = new Properties();
        try{
            FileInputStream propertiesIn = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/Config.properties");
            try {
                properties.load(propertiesIn);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String browserName = properties.getProperty("browser");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        switch ((browserName.toLowerCase())){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                getDriver().manage().window().maximize();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                getDriver().manage().window().maximize();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver.set(new InternetExplorerDriver());
                getDriver().manage().window().maximize();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver.set(new SafariDriver());
                getDriver().manage().window().maximize();
                break;
            case "iphone 11":
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--window-size=375,812");
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "android":
                chromeOptions.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                chromeOptions.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
                chromeOptions.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
                chromeOptions.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
                chromeOptions.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                try {
                    driver.set(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), chromeOptions));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Cannot start "+properties.getProperty("browser")+" browser.");
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return getDriver();
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void tearDown(){
        getDriver().manage().deleteAllCookies();
        getDriver().quit();
    }
}
