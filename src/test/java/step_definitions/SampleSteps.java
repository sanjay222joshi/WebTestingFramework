package step_definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by jorge on 26-12-2015.
 */
public class SampleSteps {
    @Given("^I do this$")
    public void i_do_this() throws Throwable {
        System.out.println("I do this");
    }

    @When("^I do that$")
    public void i_do_that() throws Throwable {
        System.out.println("I do that");
    }

    @Then("^the Other happened$")
    public void the_Other_happened() throws Throwable {
        System.out.println("the Other happened");
    }

}
