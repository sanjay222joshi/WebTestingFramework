package com.famous_smoke.automation.step_definitions.actionvalidation;

import cucumber.api.java.en.Then;

import static com.famous_smoke.automation.Hooks.testBrandPageData;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by jorge on 11-01-2016.
 */
public class ThenSteps {

    @Then("^the navigation must be valid$")
    public void the_navigation_must_be_valid() throws Throwable {
        assertThat(testBrandPageData).isNotNull();
    }

}
