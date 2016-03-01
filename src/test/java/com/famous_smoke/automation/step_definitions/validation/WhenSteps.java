package com.famous_smoke.automation.step_definitions.validation;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.actions.NavigateToBrandPageAction;
import cucumber.api.PendingException;
import cucumber.api.java.en.When;


/**
 * Created by jorge on 12-01-2016.
 */
public class WhenSteps {

    @When("^I load the brand page$")
    public void i_load_the_brand_page() throws Throwable {
        Hooks.testBrandPageData = NavigateToBrandPageAction.execute();
    }

    @When("^I load the item page$")
    public void i_load_the_item_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
