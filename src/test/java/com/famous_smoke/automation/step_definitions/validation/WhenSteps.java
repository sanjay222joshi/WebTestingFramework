package com.famous_smoke.automation.step_definitions.validation;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.actions.NavigateToBrandPageAction;
import cucumber.api.java.en.When;


/**
 * Created by jorge on 12-01-2016.
 */
public class WhenSteps {

    @When("^I load the page$")
    public void i_load_the_page() throws Throwable {
        Hooks.testBrandPageData = NavigateToBrandPageAction.execute();
    }

}
