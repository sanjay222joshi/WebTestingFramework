package step_definitions;

import api.SeleniumAction;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.PageLoader;
import modules.CheckBreadcrumbsAction;
import pageobjects.BrandPage;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by jorge on 29-12-2015.
 */
public class BreadcrumbsSteps {

    private BrandPage brandPage;

    @Given("^I want to check the breadcrumbs of \"([^\"]*)\"$")
    public void i_want_to_check_the_breadcrumbs_of(String url) throws Throwable {
        brandPage = PageLoader.loadBrand(url);
        assertThat(brandPage.hasBreadcrumbs()).isTrue();
    }

    @When("^I check all the breadcrumbs$")
    public void i_check_all_the_breadcrumbs() throws Throwable {
        SeleniumAction<BrandPage> brandPageAction = new CheckBreadcrumbsAction();
        brandPage = brandPageAction.execute(brandPage);
    }

    @Then("^the navigation must be valid$")
    public void the_navigation_must_be_valid() throws Throwable {
        assertThat(brandPage).isNotNull();
    }

}
