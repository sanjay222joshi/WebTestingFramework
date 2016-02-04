package com.famous_smoke.automation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features-processed"},
		plugin = {"pretty", "html:target/site/cucumber-pretty","json:target/cucumber.json"}
)
public class FamousSmokeTest {
}