package example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features/*.feature",
        glue = "example.stepDefination",
        tags = "@pavan",
        dryRun = true,
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports/Cucumber.json"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
