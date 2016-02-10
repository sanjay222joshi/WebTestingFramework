package com.famous_smoke.automation.step_definitions.validation;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.assertions.AssertionMessages;
import com.famous_smoke.automation.validators.UrlValidators;
import cucumber.api.java.en.Given;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by jorge on 12-01-2016.
 */
public class GivenSteps {

    @Given("^I want to the validate the brand in \"([^\"]*)\"$")
    public void i_want_to_validate_the_brand(final String url) throws Throwable {
        assertThat(UrlValidators.isBrandPage(url))
                .overridingErrorMessage(AssertionMessages.URL_NOT_BRAND, url)
                .isTrue();
        Hooks.testUrl = url;
    }

}
