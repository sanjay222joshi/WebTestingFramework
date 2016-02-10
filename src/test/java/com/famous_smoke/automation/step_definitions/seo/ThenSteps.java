package com.famous_smoke.automation.step_definitions.seo;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.assertions.PageFieldAssert;
import com.famous_smoke.automation.assertions.PageFieldListAssert;
import cucumber.api.java.en.Then;


/**
 * Created by jorge on 11-01-2016.
 */
public class ThenSteps {

    @Then("^the description should not be empty$")
    public void the_description_should_not_be_empty() throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "Description", Hooks.testBrandPageData.getDescription()
        ).isNotEmpty();
    }

    @Then("^the description should match the TestData$")
    public void the_description_should_match_the_TestData() throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "Description", Hooks.testBrandPageData.getDescription()
        ).isEqualTo(Hooks.extractedBrandPageData.getDescription());
    }

    @Then("^the header one should not be empty$")
    public void the_header_one_should_not_be_empty() throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "Header1", Hooks.testBrandPageData.getHeader1()
        ).isNotEmpty();
    }

    @Then("^the header one should match the TestData$")
    public void the_header_one_should_match_the_TestData() throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "Header1", Hooks.testBrandPageData.getHeader1()
        ).isEqualTo(Hooks.extractedBrandPageData.getHeader1());
    }

    @Then("^the breadcrumbs should not be empty$")
    public void the_breadcrumbs_should_not_be_empty() throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "Breadcrumbs Text", Hooks.testBrandPageData.getBreadcrumbsText()
        ).isNotEmpty();

        PageFieldListAssert.assertThat(
                Hooks.testUrl, "Breadcrumbs Links", Hooks.testBrandPageData.getBreadcrumbs()
        ).isNotEmpty();
    }

    @Then("^the breadcrumbs text should match the Test Data$")
    public void the_breadcrumbs_text_should_match_the_Test_Data() throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "Breadcrumbs Text", Hooks.testBrandPageData.getBreadcrumbsText()
        ).isEqualTo(Hooks.extractedBrandPageData.getBreadcrumbsText());
    }

    @Then("^the breadcrumbs links should match the Test Data$")
    public void the_breadcrumbs_links_should_match_the_Test_Data() throws Throwable {
        PageFieldListAssert.assertThat(
                Hooks.testUrl, "Breadcrumbs Links", Hooks.testBrandPageData.getBreadcrumbs()
        ).isEqualTo(Hooks.extractedBrandPageData.getBreadcrumbs());
    }

    @Then("^the canonical url should match the Test Data$")
    public void the_canonical_url_should_match_the_TestData() throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "Canonical URL", Hooks.testBrandPageData.getCanonical()
        ).isEqualTo(Hooks.extractedBrandPageData.getCanonical());
    }

    @Then("^the meta description should match the Test Data$")
    public void the_meta_description_should_match_the_TestData() throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "MetaDescription", Hooks.testBrandPageData.getMetaDescription()
        ).isEqualTo(Hooks.extractedBrandPageData.getMetaDescription());
    }

    @Then("^the meta description should be over (\\d+) characters$")
    public void the_meta_description_should_be_over_characters(final int min) throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "MetaDescription", Hooks.testBrandPageData.getMetaDescription()
        ).hasLengthGreaterThan(min);
    }

    @Then("^the meta description should be under (\\d+) characters$")
    public void the_meta_description_should_be_under_characters(final int max) throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "MetaDescription", Hooks.testBrandPageData.getMetaDescription()
        ).hasLengthLessThan(max);
    }

    @Then("^the title should match the Test Data$")
    public void the_title_should_match_the_Test_Data() throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "Title", Hooks.testBrandPageData.getTitle()
        ).isEqualTo(Hooks.extractedBrandPageData.getTitle());
    }

    @Then("^the title should be  under (\\d+) characters$")
    public void the_title_should_be_under_characters(final int max) throws Throwable {
        PageFieldAssert.assertThat(
                Hooks.testUrl, "Title", Hooks.testBrandPageData.getTitle()
        ).hasLengthLessThan(max);
    }

}
