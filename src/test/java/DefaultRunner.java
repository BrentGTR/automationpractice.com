import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"html:target/cucumber-reports/html/cucumber-pretty.html",
                "json:target/cucumber-reports/json/cucumber-report.json",
                "testng:target/testng-cucumber-reports/cuketestng.xml"},
        tags = "@Negative, ~@Failing"
)
public class DefaultRunner extends AbstractTestNGCucumberTests {
}
