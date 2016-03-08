package com.famous_smoke.automation.step_definitions.seo;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.actions.NavigateToBrandItemPageAction;
import com.famous_smoke.automation.actions.NavigateToBrandPageAction;
import com.famous_smoke.automation.actions.NavigateToPageAction;
import cucumber.api.java.en.When;

/**
 * Created by jorge on 12-01-2016.
 */
public class WhenSteps {

    @When("^I navigate to the page$")
    public void i_navigate_to_the_page() throws Throwable {
        Hooks.testBasePageData = NavigateToPageAction.execute();
    }

    @When("^I navigate to the brand page$")
    public void i_navigate_to_the_brand_page() throws Throwable {
        Hooks.testBrandPageData = NavigateToBrandPageAction.execute();
    }

    @When("^I navigate to the item page$")
    public void i_navigate_to_the_item_page() throws Throwable {
        Hooks.testBrandItemPageData = NavigateToBrandItemPageAction.execute();
    }

}
