package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features/planit.feature", glue = {"StepDefinitions"}, 
monochrome = true,
plugin = {"pretty","html:target/Reports/report.html"},
tags = "@AllTestCases")
public class testRunner {

}
