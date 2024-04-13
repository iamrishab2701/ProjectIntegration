package ProjectIntegration.TestRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features/Mobile/ProductPurchase.feature",
        glue = {"ProjectIntegration.StepDefinitions"},
        monochrome = true,
        plugin = {"pretty"},
        dryRun = false

)
public class TestRunner {
}