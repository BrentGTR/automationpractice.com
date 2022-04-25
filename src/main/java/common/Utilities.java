package common;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Utilities {

    static Logger logger = LoggerFactory.getLogger(Utilities.class);

    public static void takeScreenshot(WebDriver driver, Scenario scenario, long ms){
            try{
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(source, new File("./Screenshots/"+scenario.getName()+"/"+ms+".png"));
                logger.info("Screenshot taken");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
