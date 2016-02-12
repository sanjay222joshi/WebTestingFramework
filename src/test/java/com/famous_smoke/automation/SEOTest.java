package com.famous_smoke.automation;

import com.famous_smoke.automation.navigation.Navigator;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

/**
 * <p>This is the JUnit Test Runner for the SEO features.</p>
 *
 * <p>It's run using Cucumber-JVM runner, it checks for the features in the
 * <strong>features/procesed</strong> folder with the tag <em>@seo</em>.</p>
 *
 * <p>It publishes its results to <strong>target/cucumber-seo.json</strong></p>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features/processed"},
		plugin = {"pretty", "json:target/cucumber-seo.json"},
		tags = "@seo"
)
public class SEOTest {
	@AfterClass
	public static void closeNavigator() {
		Navigator.closeNavigator();
	}
}