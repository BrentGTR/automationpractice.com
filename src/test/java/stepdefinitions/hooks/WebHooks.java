package stepdefinitions.hooks;

import common.Utilities;
import common.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class WebHooks {
    private static WebDriver driver;

    @Before(order = 0)
    public void cleanUp(Scenario scenario){
        File directory = new File("./Screenshots/"+scenario.getName());
        if(directory.exists()){
            try {
                FileUtils.cleanDirectory(new File("./Screenshots/" + scenario.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Before(order = 1)
    public void launchBrowser(){
        driver = WebDriverFactory.initializeDriver();
    }


    @AfterStep
    public void afterStep(Scenario scenario){
        Utilities.takeScreenshot(driver, scenario, System.currentTimeMillis());
    }

    @After(order = 0)
    public void tearDown(){
        WebDriverFactory.tearDown();
    }
}
