package com.famous_smoke.automation;

import com.famous_smoke.automation.navigation.Navigator;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features/setup"},
		plugin = {"pretty", "html:target/site/cucumber-pretty","json:target/cucumber-setup.json"},
        tags = "@setup"
)
public class FeaturesSetupTest {

}