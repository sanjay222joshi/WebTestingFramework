package step_definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import factory.PageFactory;
import validators.UrlValidators;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static step_definitions.Hooks.*;

/**
 * Created by jorge on 11-01-2016.
 */
public class SeoSteps {



    @Given("^I want to the check the content of the url \"([^\"]*)\"$")
    public void i_select_the_url_from_the_TestData(String url) throws Throwable {
        Hooks.url = url;
        testBrandPageData = TEST_DATA_MAP.get(url);
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
        testBrandPage = PageFactory.createBrand(url);
    }

    @Then("^the title should match the TestData$")
    public void the_title_should_match_the_TestData() throws Throwable {
        String title = testBrandPage.getBrandData().getTitle();
        assertThat(title).isEqualTo(testBrandPageData.getTitle());
    }

    @Then("^the title should be  under (\\d+) characters$")
    public void the_title_should_be_under_characters(int max) throws Throwable {
        String title = testBrandPage.getBrandData().getTitle();
        assertThat(title.length()).isLessThanOrEqualTo(max);
    }

    @Then("^the canonical url should match the TestData$")
    public void the_canonical_url_should_match_the_TestData() throws Throwable {
        String canonical = testBrandPage.getBrandData().getCanonical();
        assertThat(canonical).isEqualTo(testBrandPageData.getCanonical());
    }

    @And("^the meta description should not be empty$")
    public void theMetaDescriptionShouldNotBeEmpty() throws Throwable {
        assertThat(testBrandPage.hasMetaDescription()).isTrue();
        String metaDescription = testBrandPage.getBrandData().getMetaDescription();
        assertThat(metaDescription).isNotEmpty();
    }

    @Then("^the meta description should match the TestData$")
    public void the_meta_description_should_match_the_TestData() throws Throwable {
        String metaDescription = testBrandPage.getBrandData().getMetaDescription();
        assertThat(metaDescription).isEqualTo(testBrandPageData.getMetaDescription());
    }

    @Then("^the meta description should be between (\\d+) and (\\d+) characters$")
    public void the_meta_description_should_be_between_and_characters(int min, int max) throws Throwable {
        String metaDescription = testBrandPage.getBrandData().getMetaDescription();
        assertThat(metaDescription.length())
                .isGreaterThanOrEqualTo(min)
                .isLessThanOrEqualTo(max);
    }

    @And("^the header one should not be empty$")
    public void theHeaderOneShouldNotBeEmpty() throws Throwable {
        assertThat(testBrandPage.hasHeader1()).isTrue();
        String header1 = testBrandPage.getBrandData().getHeader1();
        assertThat(header1).isNotEmpty();
    }

    @Then("^the header one should match the TestData$")
    public void the_header_one_should_match_the_TestData() throws Throwable {
        String header1 = testBrandPage.getBrandData().getHeader1();
        assertThat(header1).isEqualTo(testBrandPageData.getHeader1());
    }

    @And("^the description should not be empty$")
    public void theDescriptionShouldNotBeEmpty() throws Throwable {
        assertThat(testBrandPage.hasDescription()).isTrue();
        String description = testBrandPage.getBrandData().getDescription();
        assertThat(description).isNotEmpty();
    }

    @Then("^the description should match the TestData$")
    public void the_description_should_match_the_TestData() throws Throwable {
        String description = testBrandPage.getBrandData().getDescription();
        assertThat(description).isEqualTo(testBrandPageData.getDescription());
    }

    @And("^the breadcrumbs should not be empty$")
    public void theBreadcrumbsShouldNotBeEmpty() throws Throwable {
        assertThat(testBrandPage.hasBreadcrumbs()).isTrue();
    }

    @Then("^the breadcrumbs text should match the TestData$")
    public void the_breadcrumbs_text_should_match_the_TestData() throws Throwable {
        String breadcrumbs = testBrandPage.getBrandData().getBreadcrumbsText();
        assertThat(breadcrumbs).isEqualTo(testBrandPageData.getBreadcrumbsText());
    }

    @Then("^the breadcrumbs links should match the TestData$")
    public void the_breadcrumbs_links_should_match_the_TestData() throws Throwable {
        List<String> breadcrumbs = testBrandPage.getBrandData().getBreadcrumbs();
        assertThat(breadcrumbs).containsAll(testBrandPageData.getBreadcrumbs());
    }

    @Then("^the brand logo should be visible$")
    public void theBrandLogoShouldBeVisible() throws Throwable {
        assertThat(testBrandPage.hasLogo()).isTrue();
    }

    @Then("^the header section should be there$")
    public void the_header_section_should_be_there() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertThat(testBrandPage.getHeaderSection().hasSection()).isTrue();
    }

    @Then("^the top logo should be loaded$")
    public void the_top_logo_should_be_loaded() throws Throwable {
        assertThat(testBrandPage.getHeaderSection().hasTopLogo()).isTrue();
    }

    @Then("^the sitenav links should be there$")
    public void the_sitenav_links_should_be_there() throws Throwable {
        assertThat(testBrandPage.getHeaderSection().hasSiteNavLinks()).isTrue();
    }

}
