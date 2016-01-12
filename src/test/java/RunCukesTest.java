import api.BrandPageData;
import helpers.DataWorkbook;
import helpers.FeaturesProcessor;
import modules.CrawlerAction;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import step_definitions.Hooks;

import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {
				"classpath:features/SetupData.feature",
				"classpath:features-processed"
		},
		plugin = {"pretty", "html:target/cucumber-html-report","json:cucumber.json"},
		tags = {}
		)
public class RunCukesTest {
}