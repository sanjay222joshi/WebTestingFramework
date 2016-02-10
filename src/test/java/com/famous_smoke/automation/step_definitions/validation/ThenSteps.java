package com.famous_smoke.automation.step_definitions.validation;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.assertions.AssertionMessages;
import com.famous_smoke.automation.assertions.BrandIdentificationAssert;
import com.famous_smoke.automation.modules.CheckBrandLogoOrVideoAction;
import cucumber.api.java.en.Then;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by jorge on 11-01-2016.
 */
public class ThenSteps {

    @Then("^the brand identification should be visible$")
    public void the_brand_identification_should_be_vivisble() throws Throwable {
        BrandIdentificationAssert.assertThat(
                Hooks.testUrl, CheckBrandLogoOrVideoAction.execute()
        ).isIdentified();
    }

}
