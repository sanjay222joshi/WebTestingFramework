package com.famous_smoke.automation.step_definitions.seo;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.modules.CheckBrandLogoAction;
import cucumber.api.java.en.Then;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

/**
 * Created by jorge on 11-01-2016.
 */
public class ThenSteps {

    @Then("^the description should not be empty$")
    public void the_description_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBrandPageData.getDescription())
                .isNotEmpty();
    }

    @Then("^the description should match the TestData$")
    public void the_description_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandPageData.getDescription())
                .isEqualTo(Hooks.extractedBrandPageData.getDescription());
    }

    @Then("^the header one should not be empty$")
    public void the_header_one_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBrandPageData.getHeader1())
                .isNotEmpty();
    }

    @Then("^the header one should match the TestData$")
    public void the_header_one_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandPageData.getHeader1())
                .isEqualTo(Hooks.extractedBrandPageData.getHeader1());
    }

    @Then("^the brand logo should be visible$")
    public void the_brand_logo_should_be_visible() throws Throwable {
        assertThat(CheckBrandLogoAction.execute())
                .isNotNull();
    }

    @Then("^the header section should be there$")
    public void the_header_section_should_be_there() throws Throwable {
        fail("complete this test");
    }

    @Then("^the top logo should be loaded$")
    public void the_top_logo_should_be_loaded() throws Throwable {
        //TODO Complete this test
        fail("complete this test");
    }

    @Then("^the sitenav links should be there$")
    public void the_sitenav_links_should_be_there() throws Throwable {
        //TODO Complete this test
        fail("complete this test");
    }

    @Then("^the breadcrumbs should not be empty$")
    public void the_breadcrumbs_should_not_be_empty() throws Throwable {
        assertThat(Hooks.testBrandPageData.getBreadcrumbsText())
                .isNotEmpty();
        assertThat(Hooks.testBrandPageData.getBreadcrumbs())
                .isNotEmpty();
    }

    @Then("^the breadcrumbs text should match the Test Data$")
    public void the_breadcrumbs_text_should_match_the_Test_Data() throws Throwable {
        assertThat(Hooks.testBrandPageData.getBreadcrumbsText())
                .isEqualTo(Hooks.extractedBrandPageData.getBreadcrumbsText());
    }

    @Then("^the breadcrumbs links should match the Test Data$")
    public void the_breadcrumbs_links_should_match_the_Test_Data() throws Throwable {
        assertThat(Hooks.testBrandPageData.getBreadcrumbs())
                .isEqualTo(Hooks.extractedBrandPageData.getBreadcrumbs());
    }

    @Then("^the canonical url should match the Test Data$")
    public void the_canonical_url_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandPageData.getCanonical())
                .isEqualTo(Hooks.extractedBrandPageData.getCanonical());
    }

    @Then("^the meta description should match the Test Data$")
    public void the_meta_description_should_match_the_TestData() throws Throwable {
        assertThat(Hooks.testBrandPageData.getMetaDescription())
                .isEqualTo(Hooks.extractedBrandPageData.getMetaDescription());
    }

    @Then("^the meta description should be over (\\d+) characters$")
    public void the_meta_description_should_be_over_characters(final int min) throws Throwable {
        assertThat(Hooks.testBrandPageData.getMetaDescription().length())
                .isGreaterThanOrEqualTo(min);
    }

    @Then("^the meta description should be under (\\d+) characters$")
    public void the_meta_description_should_be_under_characters(final int max) throws Throwable {
        assertThat(Hooks.testBrandPageData.getMetaDescription().length())
                .isLessThanOrEqualTo(max);
    }

    @Then("^the title should match the Test Data$")
    public void the_title_should_match_the_Test_Data() throws Throwable {
        assertThat(Hooks.testBrandPageData.getTitle())
                .isEqualTo(Hooks.extractedBrandPageData.getTitle());
    }

    @Then("^the title should be  under (\\d+) characters$")
    public void the_title_should_be_under_characters(final int max) throws Throwable {
        assertThat(Hooks.testBrandPageData.getTitle().length())
                .isLessThanOrEqualTo(max);
    }

}
