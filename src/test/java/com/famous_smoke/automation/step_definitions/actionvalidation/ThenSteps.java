package com.famous_smoke.automation.step_definitions.actionvalidation;

import com.famous_smoke.automation.Hooks;
import cucumber.api.java.en.Then;

import static com.famous_smoke.automation.Hooks.testBrandPageData;
import static com.famous_smoke.automation.assertions.FamousSmokeAssertions.assertThat;

/**
 * Created by jorge on 11-01-2016.
 */
public class ThenSteps {

    @Then("^the navigation must be valid$")
    public void the_navigation_must_be_valid() throws Throwable {
        assertThat(Hooks.testBasePageData).isNotNull();
    }

}
