package com.famous_smoke.automation;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * <p>This is the JUnit Test Runner for the Setup features.</p>
 *
 * <p>It's run using Cucumber-JVM runner, it checks for the features in the
 * <strong>features/setup</strong> folder with the tag <em>@setup</em>.</p>
 *
 * <p>It publishes the result to <strong>target/cucumber-setup.json</strong></p>
 *
 * <p>As of now the setup tests must be run before everything else in order
 * to process the features templates and create the processed features.</p>
 *
 * <p>We guarantee this running one of the jenkins scripts before calling
 * maven directly.</p>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features/setup/"},
		plugin = {"pretty", "json:target/cucumber-setup.json"},
        tags = "@setup"
)
public class FeaturesSetupTest {

}