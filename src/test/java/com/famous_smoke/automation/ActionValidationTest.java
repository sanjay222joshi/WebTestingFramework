package com.famous_smoke.automation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * <p>This is the JUnit Test Runner for the Action Validation features.</p>
 *
 * <p>It's run using Cucumber-JVM runner, it checks for the features in the
 * <strong>features/procesed</strong> folder with the tag <em>@actionvalidation</em>.</p>
 *
 * <p>It publishes its result to <strong>target/cucumber-actionvalidation.json</strong></p>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features/processed"},
		plugin = {"pretty", "json:target/cucumber-actionvalidation.json"},
		tags = "@actionvalidation"
)
public class ActionValidationTest {

}