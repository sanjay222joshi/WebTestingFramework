package step_definitions;

import api.SeleniumAction;
import cucumber.api.PendingException;
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

    private String url;
    private BrandPage brandPage;

    @Given("^I select the url \"([^\"]*)\" from the TestData$")
    public void i_select_the_url_from_the_TestData(String url) throws Throwable {
        this.url = url;
    }

    @When("^I navigate to the brand page$")
    public void i_navigate_to_the_brand_page() throws Throwable {
        this.brandPage = PageLoader.loadBrand(url);
        this.brandPage.initializeElements();
    }

    @Then("^the title should match the TestData$")
    public void the_title_should_match_the_TestData() throws Throwable {
        // TODO Write code here that turns the phrase above into concrete actions
    }

    @Then("^the canonical url should match the TestData$")
    public void the_canonical_url_should_match_the_TestData() throws Throwable {
        // TODO Write code here that turns the phrase above into concrete actions
    }

    @Then("^the meta description should match the TestData$")
    public void the_meta_description_should_match_the_TestData() throws Throwable {
        // TODO Write code here that turns the phrase above into concrete actions
    }

    @Then("^the h(\\d+) should be should match the TestData$")
    public void the_h_should_be_should_match_the_TestData(int arg1) throws Throwable {
        // TODO Write code here that turns the phrase above into concrete actions
    }

    @Then("^the description should match the TestData$")
    public void the_description_should_match_the_TestData() throws Throwable {
        // TODO Write code here that turns the phrase above into concrete actions
    }

    @Then("^the breadcrumbs should match the TestData$")
    public void the_breadcrumbs_should_match_the_TestData() throws Throwable {
        // TODO Write code here that turns the phrase above into concrete actions
    }

    @Then("^the breadcrumbs links should match the TestData$")
    public void the_breadcrumbs_links_should_match_the_TestData() throws Throwable {
        // TODO Write code here that turns the phrase above into concrete actions
    }

}
