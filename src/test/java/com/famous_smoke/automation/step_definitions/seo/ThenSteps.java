package com.famous_smoke.automation.step_definitions.seo;

import com.famous_smoke.automation.Hooks;
import cucumber.api.java.en.Then;

import static com.famous_smoke.automation.assertions.FamousSmokeAssertions.assertThat;


/**
 * Created by jorge on 11-01-2016.
 */
public class ThenSteps {

    @Then("^the brand description should not be empty$")
    public void the_brand_description_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasDescription();
    }

    @Then("^the brand description should match the TestData$")
    public void the_brand_description_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasDescriptionEqualTo(Hooks.extractedBrandPageData.getDescription());
    }

    @Then("^the brand header one should not be empty$")
    public void the_brand_header_one_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasHeader1();
    }

    @Then("^the brand header one should match the TestData$")
    public void the_brand_header_one_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandPageData)
                .hasHeader1EqualTo(Hooks.extractedBrandPageData.getHeader1());
    }

    @Then("^the item description should not be empty$")
    public void the_item_description_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBrandItemPageData)
                .hasDescription();
    }

    @Then("^the item description should match the TestData$")
    public void the_item_description_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandItemPageData)
                .hasDescriptionEqualTo(Hooks.extractedBrandPageData.getDescription());
    }

    @Then("^the item header one should not be empty$")
    public void the_item_header_one_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBrandItemPageData)
                .hasHeader1();
    }

    @Then("^the item header one should match the TestData$")
    public void the_item_header_one_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandItemPageData)
                .hasHeader1EqualTo(Hooks.extractedBrandPageData.getHeader1());
    }

    @Then("^the breadcrumbs should not be empty$")
    public void the_breadcrumbs_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBasePageData)
                .hasBreadcrumbs();

        assertThat(Hooks.testBasePageData)
                .hasBreadcrumbsText();
    }

    @Then("^the breadcrumbs text should match the Test Data$")
    public void the_breadcrumbs_text_should_match_the_Test_Data() throws Throwable {
        assertThat(Hooks.testBasePageData)
                .hasBreadcrumbsTextEqualTo(Hooks.extractedBrandPageData.getBreadcrumbsText());
    }

    @Then("^the breadcrumbs links should match the Test Data$")
    public void the_breadcrumbs_links_should_match_the_Test_Data() throws Throwable {
        assertThat(Hooks.testBasePageData)
                .hasBreadcrumbsEqualTo(Hooks.extractedBrandPageData.getBreadcrumbsLinks());
    }

    @Then("^the canonical url should match the Test Data$")
    public void the_canonical_url_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBasePageData)
                .hasCanonicalEqualTo(Hooks.extractedBrandPageData.getCanonical());
    }

    @Then("^the meta description should match the Test Data$")
    public void the_meta_description_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBasePageData)
                .hasMetaDescriptionEqualTo(Hooks.extractedBrandPageData.getMetaDescription());
    }

    @Then("^the meta description should be over (\\d+) characters$")
    public void the_meta_description_should_be_over_characters(final int min) throws Throwable {
        assertThat(Hooks.testBasePageData)
                .hasMetaDescriptionLengthGreaterThan(min);
    }

    @Then("^the meta description should be under (\\d+) characters$")
    public void the_meta_description_should_be_under_characters(final int max) throws Throwable {
        assertThat(Hooks.testBasePageData)
                .hasMetaDescriptionLengthLessThan(max);
    }

    @Then("^the title should match the Test Data$")
    public void the_title_should_match_the_Test_Data() throws Throwable {
        assertThat(Hooks.testBasePageData)
                .hasTitle();

        assertThat(Hooks.testBasePageData)
                .hasTitleEqualTo(Hooks.extractedBrandPageData.getTitle());
    }

    @Then("^the title should be  under (\\d+) characters$")
    public void the_title_should_be_under_characters(final int max) throws Throwable {
        assertThat(Hooks.testBasePageData)
                .hasTitleLengthLessThan(max);
    }

}
