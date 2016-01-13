package com.famous_smoke.automation.step_definitions.validation;

import com.famous_smoke.automation.modules.CheckBreadcrumbsAction;
import com.famous_smoke.automation.pageobjects.BrandPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.famous_smoke.automation.factory.PageFactory;

import static org.fest.assertions.api.Assertions.assertThat;
import static com.famous_smoke.automation.Hooks.testBrandPage;

/**
 * Created by jorge on 11-01-2016.
 */
public class ThenSteps {

    @Then("^the navigation must be valid$")
    public void the_navigation_must_be_valid() throws Throwable {
        assertThat(testBrandPage).isNotNull();
        testBrandPage.getDriver().close();
    }

}
