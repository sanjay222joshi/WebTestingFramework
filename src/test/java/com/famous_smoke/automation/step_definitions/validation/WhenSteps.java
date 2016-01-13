package com.famous_smoke.automation.step_definitions.validation;

import com.famous_smoke.automation.modules.CheckBreadcrumbsAction;
import cucumber.api.java.en.When;

import static com.famous_smoke.automation.Hooks.testBrandPage;

/**
 * Created by jorge on 12-01-2016.
 */
public class WhenSteps {

    @When("^I check all the breadcrumbs$")
    public void i_check_all_the_breadcrumbs() throws Throwable {
        testBrandPage = CheckBreadcrumbsAction.execute(testBrandPage);
    }

}
