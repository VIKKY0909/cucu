package testrunners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags="@LoginPositive",
        glue = {"stepDefinations"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-report.json"},
        monochrome = true
)
public class TestRunner {
}
