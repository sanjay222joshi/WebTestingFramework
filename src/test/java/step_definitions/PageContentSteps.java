package step_definitions;

import api.SeleniumAction;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.PageLoader;
import modules.CheckLogoAction;
import pageobjects.BrandPage;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by jorge on 26-12-2015.
 */
public class PageContentSteps {

    private SeleniumAction<BrandPage> action;

    private String url;
    private BrandPage brandPage;

    @Given("^I select the url from the TestData$")
    public void i_select_the_url_from_the_TestData() throws Throwable {
        this.url = "https://www.famous-smoke.com/brand/262+allegiance+cigars";
    }

    @When("^I navigate to the brand page$")
    public void i_navigate_to_the_brand_page() throws Throwable {
        this.brandPage = PageLoader.loadBrand(url);
        this.brandPage.initializeElements();
    }

    @Then("^the logo should load and be visible$")
    public void the_logo_should_load_and_be_visible() throws Throwable {
        action = new CheckLogoAction();
        assertThat(action.execute(brandPage)).isNotNull();
    }

}
