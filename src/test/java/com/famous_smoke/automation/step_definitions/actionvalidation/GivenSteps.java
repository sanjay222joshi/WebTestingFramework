package com.famous_smoke.automation.step_definitions.actionvalidation;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.modules.NavigateToBrandPageAction;
import cucumber.api.java.en.Given;

import static com.famous_smoke.automation.assertions.FamousSmokeAssertions.assertThat;


/**
 * Created by jorge on 12-01-2016.
 */
public class GivenSteps {

    @Given("^I want to check the breadcrumbs of \"([^\"]*)\"$")
    public void i_want_to_check_the_breadcrumbs_of(final String url) throws Throwable {
        Hooks.testUrl = url;
        Hooks.testBrandPageData = NavigateToBrandPageAction.execute();
        assertThat(Hooks.testBrandPageData).hasBreadcrumbs();
        Hooks.testBrandPageData = null;
    }
}
