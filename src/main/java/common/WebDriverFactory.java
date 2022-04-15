package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class WebDriverFactory {
    static Properties properties = null;
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initializeDriver(){
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

        if("chrome".equalsIgnoreCase(browserName)){
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if("firefox".equalsIgnoreCase(browserName)){
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else{
            System.out.println("Cannot start "+properties.getProperty("browser")+" browser.");
        }

        getDriver().manage().window().maximize();
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
