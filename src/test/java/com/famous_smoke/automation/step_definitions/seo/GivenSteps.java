package com.famous_smoke.automation.step_definitions.seo;

import com.famous_smoke.automation.Hooks;
import cucumber.api.java.en.Given;

import static com.famous_smoke.automation.assertions.FamousSmokeAssertions.assertThat;

/**
 * Created by jorge on 12-01-2016.
 */
public class GivenSteps {

    @Given("^I want to the check the content of the url \"([^\"]*)\"$")
    public void i_select_the_url_from_the_TestData(final String url) throws Throwable {
        Hooks.testUrl = url;
        Hooks.extractedBasePageData = Hooks.TEST_BASE_DATA_MAP.get(url);
    }

    @Given("^I want to the check the content of the brand url \"([^\"]*)\"$")
    public void i_want_to_the_check_the_content_of_the_brand_url(final String url) throws Throwable {
        Hooks.testUrl = url;
        Hooks.extractedBrandPageData = Hooks.TEST_BRAND_DATA_MAP.get(Hooks.testUrl);
    }

    @Given("^I want to the check the content of the item url \"([^\"]*)\"$")
    public void i_want_to_the_check_the_content_of_the_item_url(final String url) throws Throwable {
        Hooks.testUrl = url;
        Hooks.extractedBrandItemPageData = Hooks.TEST_ITEM_DATA_MAP.get(Hooks.testUrl);
    }

    @Given("^the url is from a brand page$")
    public void the_url_is_from_a_brand_page() throws Throwable {
        assertThat(Hooks.testUrl).isBrandURL();
        Hooks.extractedBrandPageData = Hooks.TEST_BRAND_DATA_MAP.get(Hooks.testUrl);
    }

    @Given("^the url is from a brand group page$")
    public void theUrlIsFromABrandGroupPage() throws Throwable {
        assertThat(Hooks.testUrl).isBrandGroupURL();
        Hooks.extractedBrandPageData = Hooks.TEST_BRAND_DATA_MAP.get(Hooks.testUrl);
    }

    @Given("^the url is from an item page$")
    public void theUrlIsFromAnItemPage() throws Throwable {
        assertThat(Hooks.testUrl).isItemURL();
    }

}
