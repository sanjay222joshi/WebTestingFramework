package com.famous_smoke.automation.step_definitions.setup;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.actions.ScrapBrandItemsDataFromBrandListPageAction;
import com.famous_smoke.automation.actions.NavigateToTestUrlAction;
import com.famous_smoke.automation.util.FeaturesProcessor;
import com.famous_smoke.automation.util.TestConfigReader;
import com.famous_smoke.automation.actions.ScrapBrandsDataFromBrandListPageAction;
import cucumber.api.java.en.Given;


/**
 * Created by jorge on 27-12-2015.
 */
public class GivenSteps {

    @Given("^I need to Setup the Features$")
    public void i_need_to_Setup_the_Features() throws Throwable {
        Hooks.testSetupNeeded = FeaturesProcessor.needToProcess();
    }

    @Given("^I navigate to the page \"([^\"]*)\"$")
    public void i_navigate_to_the_page(final String url) throws Throwable {
        if (Hooks.testSetupNeeded) {
            Hooks.testUrl = url;
            Hooks.testMaximumCrawls = TestConfigReader.getMaximumCrawls();
            NavigateToTestUrlAction.execute();
        }
    }

    @Given("^I crawl through the brands list$")
    public void i_crawl_through_the_brands_list() throws Throwable {
        if (Hooks.testSetupNeeded) {
            Hooks.testBrandPagesData = ScrapBrandsDataFromBrandListPageAction.execute();
        }
    }

    @Given("^I crawl through the items of each brand$")
    public void i_crawl_through_the_items_of_each_brand() throws Throwable {
        if (Hooks.testSetupNeeded) {
            Hooks.testBrandItemPagesData = ScrapBrandItemsDataFromBrandListPageAction.execute();
        }
    }

}
