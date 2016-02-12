package com.famous_smoke.automation;

import com.famous_smoke.automation.navigation.Navigator;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features/processed"},
		plugin = {"pretty", "html:target/site/cucumber-validation-pretty","json:target/cucumber-validation.json"},
		tags = "@validation"
)
public class ValidationTest {

}