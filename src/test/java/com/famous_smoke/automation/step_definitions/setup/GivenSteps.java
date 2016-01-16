package com.famous_smoke.automation.step_definitions.setup;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.helpers.FeaturesProcessor;
import com.famous_smoke.automation.helpers.TestConfigReader;
import com.famous_smoke.automation.modules.CrawlThroughBrandsAction;
import com.famous_smoke.automation.modules.NavigateToBrandPageAction;
import cucumber.api.java.en.Given;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by jorge on 27-12-2015.
 */
public class GivenSteps {

    @Given("^I need to Setup the Features$")
    public void i_need_to_Setup_the_Features() throws Throwable {
        assertThat(FeaturesProcessor.needToProcess()).isTrue();
    }

    @Given("^I navigate to the page \"([^\"]*)\"$")
    public void i_navigate_to_the_page(final String url) throws Throwable {
        Hooks.testUrl = url;
        Hooks.testMaximumCrawls = TestConfigReader.getMaximumCrawls();
        NavigateToBrandPageAction.execute();
    }

    @Given("^I crawl through the list$")
    public void i_crawl_through_the_list() throws Throwable {
        Hooks.testBrandPagesData = CrawlThroughBrandsAction.execute();
    }

}