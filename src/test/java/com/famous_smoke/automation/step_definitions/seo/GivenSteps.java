package com.famous_smoke.automation.step_definitions.seo;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.assertions.UrlAssert;
import cucumber.api.java.en.Given;

import static com.famous_smoke.automation.Hooks.TEST_DATA_MAP;

/**
 * Created by jorge on 12-01-2016.
 */
public class GivenSteps {


    @Given("^I want to the check the content of the url \"([^\"]*)\"$")
    public void i_select_the_url_from_the_TestData(final String url) throws Throwable {
        Hooks.testUrl = url;
        Hooks.extractedBrandPageData = TEST_DATA_MAP.get(url);
    }

    @Given("^the url is from a brand page$")
    public void the_url_is_from_a_brand_page() throws Throwable {
        UrlAssert.assertThat(Hooks.testUrl).isBrandURL();
    }

    @Given("^the url is from a brand group page$")
    public void theUrlIsFromABrandGroupPage() throws Throwable {
        UrlAssert.assertThat(Hooks.testUrl).isBrandGroupURL();
    }

}
