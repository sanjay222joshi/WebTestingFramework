package step_definitions;

import api.SeleniumAction;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import factory.PageFactory;
import modules.CheckBreadcrumbsAction;
import pageobjects.BrandPage;

import static org.fest.assertions.api.Assertions.assertThat;
import static step_definitions.Hooks.testBrandPage;

/**
 * Created by jorge on 11-01-2016.
 */
public class ValidationSteps {


    @Given("^I want to check the breadcrumbs of \"([^\"]*)\"$")
    public void i_want_to_check_the_breadcrumbs_of(String url) throws Throwable {
        testBrandPage = PageFactory.createBrand(url);
        assertThat(testBrandPage.hasBreadcrumbs()).isTrue();
    }

    @When("^I check all the breadcrumbs$")
    public void i_check_all_the_breadcrumbs() throws Throwable {
        SeleniumAction<BrandPage> brandPageAction = new CheckBreadcrumbsAction();
        testBrandPage = brandPageAction.execute(testBrandPage);
    }

    @Then("^the navigation must be valid$")
    public void the_navigation_must_be_valid() throws Throwable {
        assertThat(testBrandPage).isNotNull();
        testBrandPage.getDriver().close();
    }

}
