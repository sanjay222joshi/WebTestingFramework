package com.famous_smoke.automation.step_definitions.validation;


import com.famous_smoke.automation.Hooks;
import cucumber.api.java.en.Then;

import static com.famous_smoke.automation.assertions.FamousSmokeAssertions.assertThat;


/**
 * Created by jorge on 11-01-2016.
 */
public class ThenSteps {

    @Then("^the brand identification should be visible$")
    public void the_brand_identification_should_be_vivisble() throws Throwable {
        assertThat(Hooks.testBrandPageData).isIdentified();
    }

}
