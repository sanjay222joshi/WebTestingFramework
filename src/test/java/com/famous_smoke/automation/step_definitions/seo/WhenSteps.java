package com.famous_smoke.automation.step_definitions.seo;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.modules.NavigateToBrandPageAction;
import cucumber.api.java.en.When;

/**
 * Created by jorge on 12-01-2016.
 */
public class WhenSteps {

    @When("^I navigate to the page$")
    public void i_navigate_to_the_page() throws Throwable {
        Hooks.testBrandPageData = NavigateToBrandPageAction.execute();
    }

}
