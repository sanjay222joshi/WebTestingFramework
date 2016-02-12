package com.famous_smoke.automation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * <p>This is the JUnit Test Runner for the Validation features.</p>
 *
 * <p>It's run using Cucumber-JVM runner, it checks for the features in the
 * <strong>features/procesed</strong> folder with the tag <em>@validation</em>.</p>
 *
 * <p>It publishes its results to <strong>target/cucumber-validation.json</strong></p>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features/processed"},
		plugin = {"pretty", "json:target/cucumber-validation.json"},
		tags = "@validation"
)
public class ValidationTest {

}