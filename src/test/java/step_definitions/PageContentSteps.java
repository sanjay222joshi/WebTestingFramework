package step_definitions;

import api.BrandPageData;
import api.SeleniumAction;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.PageLoader;
import modules.CheckLogoAction;
import pageobjects.BrandPage;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by jorge on 26-12-2015.
 */
public class PageContentSteps {

    private String url;
    private BrandPage brandPage;
    private BrandPageData testData;

    @Given("^I select the url \"([^\"]*)\" from the TestData$")
    public void i_select_the_url_from_the_TestData(String url) throws Throwable {
        this.url = url;
        testData = Hooks.createTestDataMap().get(url);
    }

    @When("^I navigate to the brand page$")
    public void i_navigate_to_the_brand_page() throws Throwable {
        brandPage = PageLoader.loadBrand(url);
    }

    @Then("^the title should match the TestData$")
    public void the_title_should_match_the_TestData() throws Throwable {
        String title = brandPage.getBrandData().getTitle();
        assertThat(title).isEqualTo(testData.getTitle());
    }

    @Then("^the canonical url should match the TestData$")
    public void the_canonical_url_should_match_the_TestData() throws Throwable {
        String canonical = brandPage.getBrandData().getCanonical();
        assertThat(canonical).isEqualTo(testData.getCanonical());
    }

    @Then("^the meta description should match the TestData$")
    public void the_meta_description_should_match_the_TestData() throws Throwable {
        String metaDescription = brandPage.getBrandData().getMetaDescription();
        assertThat(metaDescription).isEqualTo(testData.getMetaDescription());
    }

    @Then("^the header one should match the TestData$")
    public void the_header_one_should_match_the_TestData() throws Throwable {
        String header1 = brandPage.getBrandData().getHeader1();
        assertThat(header1).isEqualTo(testData.getHeader1());
    }

    @Then("^the description should match the TestData$")
    public void the_description_should_match_the_TestData() throws Throwable {
        String description = brandPage.getBrandData().getDescription();
        assertThat(description).isEqualTo(testData.getDescription());
    }

    @Then("^the breadcrumbs should match the TestData$")
    public void the_breadcrumbs_should_match_the_TestData() throws Throwable {
        String breadcrumbs = brandPage.getBrandData().getBreadcrumbsText();
        assertThat(breadcrumbs).isEqualTo(testData.getBreadcrumbsText());
    }

    @Then("^the breadcrumbs links should match the TestData$")
    public void the_breadcrumbs_links_should_match_the_TestData() throws Throwable {
        List<String> breadcrumbs = brandPage.getBrandData().getBreadcrumbs();
        assertThat(breadcrumbs).containsAll(testData.getBreadcrumbs());
    }

}
