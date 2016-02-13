package com.famous_smoke.automation.step_definitions.seo;

import com.famous_smoke.automation.Hooks;
import cucumber.api.java.en.Then;

import static com.famous_smoke.automation.assertions.FamousSmokeAssertions.assertThat;


/**
 * Created by jorge on 11-01-2016.
 */
public class ThenSteps {

    @Then("^the description should not be empty$")
    public void the_description_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasDescription();
    }

    @Then("^the description should match the TestData$")
    public void the_description_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasDescriptionEqualTo(Hooks.extractedBrandPageData.getDescription());
    }

    @Then("^the header one should not be empty$")
    public void the_header_one_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasHeader1();
    }

    @Then("^the header one should match the TestData$")
    public void the_header_one_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasHeader1EqualTo(Hooks.extractedBrandPageData.getHeader1());
    }

    @Then("^the breadcrumbs should not be empty$")
    public void the_breadcrumbs_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasBreadcrumbs();

        assertThat(Hooks.testBrandPageData)
                .hasBreadcrumbsText();
    }

    @Then("^the breadcrumbs text should match the Test Data$")
    public void the_breadcrumbs_text_should_match_the_Test_Data() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasBreadcrumbsTextEqualTo(Hooks.extractedBrandPageData.getBreadcrumbsText());
    }

    @Then("^the breadcrumbs links should match the Test Data$")
    public void the_breadcrumbs_links_should_match_the_Test_Data() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasBreadcrumbsEqualTo(Hooks.extractedBrandPageData.getBreadcrumbsLinks());
    }

    @Then("^the canonical url should match the Test Data$")
    public void the_canonical_url_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasCanonicalEqualTo(Hooks.extractedBrandPageData.getCanonical());
    }

    @Then("^the meta description should match the Test Data$")
    public void the_meta_description_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasMetaDescriptionEqualTo(Hooks.extractedBrandPageData.getMetaDescription());
    }

    @Then("^the meta description should be over (\\d+) characters$")
    public void the_meta_description_should_be_over_characters(final int min) throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasMetaDescriptionLengthGreaterThan(min);
    }

    @Then("^the meta description should be under (\\d+) characters$")
    public void the_meta_description_should_be_under_characters(final int max) throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasMetaDescriptionLengthLessThan(max);
    }

    @Then("^the title should match the Test Data$")
    public void the_title_should_match_the_Test_Data() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasTitle();

        assertThat(Hooks.testBrandPageData)
                .hasTitleEqualTo(Hooks.extractedBrandPageData.getTitle());
    }

    @Then("^the title should be  under (\\d+) characters$")
    public void the_title_should_be_under_characters(final int max) throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasTitleLengthLessThan(max);
    }

}
