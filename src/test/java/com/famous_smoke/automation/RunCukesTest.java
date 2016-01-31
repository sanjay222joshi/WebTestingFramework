package com.famous_smoke.automation;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		strict = false,
		features = {
				"classpath:features/setup",
				"classpath:features-processed"
		},
		plugin = {"pretty", "html:target/site/cucumber-pretty","json:target/cucumber.json"},
		tags = {}
		)
public class RunCukesTest {
}