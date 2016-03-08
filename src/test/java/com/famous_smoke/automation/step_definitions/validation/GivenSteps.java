package com.famous_smoke.automation.step_definitions.validation;

import com.famous_smoke.automation.Hooks;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

import static com.famous_smoke.automation.assertions.FamousSmokeAssertions.assertThat;

/**
 * Created by jorge on 12-01-2016.
 */
public class GivenSteps {

    @Given("^I want to the validate the brand in \"([^\"]*)\"$")
    public void i_want_to_validate_the_brand(final String url) throws Throwable {
        assertThat(url).isBrandURL();
        Hooks.testUrl = url;
    }

    @Given("^I want to the validate the item in \"([^\"]*)\"$")
    public void i_want_to_the_validate_the_item_in(final String url) throws Throwable {
        assertThat(url).isItemURL();
        Hooks.testUrl = url;
    }

}
