package com.famous_smoke.automation.step_definitions.validation;

import com.famous_smoke.automation.factory.PageFactory;
import cucumber.api.java.en.Given;

import static com.famous_smoke.automation.Hooks.testBrandPage;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by jorge on 12-01-2016.
 */
public class GivenSteps {

    @Given("^I want to check the breadcrumbs of \"([^\"]*)\"$")
    public void i_want_to_check_the_breadcrumbs_of(final String url) throws Throwable {
        testBrandPage = PageFactory.createBrand(url);
        assertThat(testBrandPage.hasBreadcrumbs()).isTrue();
    }
}
