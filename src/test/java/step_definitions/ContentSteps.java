package step_definitions;

import api.BrandPageData;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.PageLoader;
import pageobjects.BrandPage;
import validators.UrlValidators;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by jorge on 26-12-2015.
 */
public class ContentSteps {

    private String url;
    private BrandPage brandPage;
    private BrandPageData testData;

    @Given("^I want to the check the content of the url \"([^\"]*)\"$")
    public void i_select_the_url_from_the_TestData(String url) throws Throwable {
        this.url = url;
        testData = Hooks.createTestDataMap().get(url);
    }

    @And("^the url is from a brand page$")
    public void the_url_is_from_a_brand_page() throws Throwable {
        assertThat(UrlValidators.isBrandPage(url)).isTrue();
    }

    @And("^the url is from a brand group page$")
    public void theUrlIsFromABrandGroupPage() throws Throwable {
        assertThat(UrlValidators.isBrandGroupPage(url)).isTrue();
    }

    @When("^I navigate to the page$")
    public void i_navigate_to_the_brand_page() throws Throwable {
        brandPage = PageLoader.loadBrand(url);
    }

    @Then("^the title should match the TestData$")
    public void the_title_should_match_the_TestData() throws Throwable {
        String title = brandPage.getBrandData().getTitle();
        assertThat(title).isEqualTo(testData.getTitle());
    }

    @Then("^the title should be  under (\\d+) characters$")
    public void the_title_should_be_under_characters(int max) throws Throwable {
        String title = brandPage.getBrandData().getTitle();
        assertThat(title.length()).isLessThanOrEqualTo(max);
    }


    @Then("^the canonical url should match the TestData$")
    public void the_canonical_url_should_match_the_TestData() throws Throwable {
        String canonical = brandPage.getBrandData().getCanonical();
        assertThat(canonical).isEqualTo(testData.getCanonical());
    }

    @And("^the meta description should not be empty$")
    public void theMetaDescriptionShouldNotBeEmpty() throws Throwable {
        assertThat(brandPage.hasMetaDescription()).isTrue();
        String metaDescription = brandPage.getBrandData().getMetaDescription();
        assertThat(metaDescription).isNotEmpty();
    }

    @Then("^the meta description should match the TestData$")
    public void the_meta_description_should_match_the_TestData() throws Throwable {
        String metaDescription = brandPage.getBrandData().getMetaDescription();
        assertThat(metaDescription).isEqualTo(testData.getMetaDescription());
    }

    @Then("^the meta description should be between (\\d+) and (\\d+) characters$")
    public void the_meta_description_should_be_between_and_characters(int min, int max) throws Throwable {
        String metaDescription = brandPage.getBrandData().getMetaDescription();
        assertThat(metaDescription.length())
                .isGreaterThanOrEqualTo(min)
                .isLessThanOrEqualTo(max);
    }

    @And("^the header one should not be empty$")
    public void theHeaderOneShouldNotBeEmpty() throws Throwable {
        assertThat(brandPage.hasHeader1()).isTrue();
        String header1 = brandPage.getBrandData().getHeader1();
        assertThat(header1).isNotEmpty();
    }

    @Then("^the header one should match the TestData$")
    public void the_header_one_should_match_the_TestData() throws Throwable {
        String header1 = brandPage.getBrandData().getHeader1();
        assertThat(header1).isEqualTo(testData.getHeader1());
    }

    @And("^the description should not be empty$")
    public void theDescriptionShouldNotBeEmpty() throws Throwable {
        assertThat(brandPage.hasDescription()).isTrue();
        String description = brandPage.getBrandData().getDescription();
        assertThat(description).isNotEmpty();
    }

    @Then("^the description should match the TestData$")
    public void the_description_should_match_the_TestData() throws Throwable {
        String description = brandPage.getBrandData().getDescription();
        assertThat(description).isEqualTo(testData.getDescription());
    }

    @And("^the breadcrumbs should not be empty$")
    public void theBreadcrumbsShouldNotBeEmpty() throws Throwable {
        assertThat(brandPage.hasBreadcrumbs()).isTrue();
    }

    @Then("^the breadcrumbs text should match the TestData$")
    public void the_breadcrumbs_text_should_match_the_TestData() throws Throwable {
        String breadcrumbs = brandPage.getBrandData().getBreadcrumbsText();
        assertThat(breadcrumbs).isEqualTo(testData.getBreadcrumbsText());
    }

    @Then("^the breadcrumbs links should match the TestData$")
    public void the_breadcrumbs_links_should_match_the_TestData() throws Throwable {
        List<String> breadcrumbs = brandPage.getBrandData().getBreadcrumbs();
        assertThat(breadcrumbs).containsAll(testData.getBreadcrumbs());
    }

    @Then("^the brand logo should be visible$")
    public void theBrandLogoShouldBeVisible() throws Throwable {
        assertThat(brandPage.hasLogo()).isTrue();
    }

    @Given("^I want to check the breadcrumbs of <URL>$")
    public void iWantToCheckTheBreadcrumbsOfURL() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


}
