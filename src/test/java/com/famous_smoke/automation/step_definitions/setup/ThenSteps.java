package com.famous_smoke.automation.step_definitions.setup;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.util.FeaturesProcessor;
import cucumber.api.java.en.Then;

import static com.famous_smoke.automation.assertions.FamousSmokeAssertions.fail;

/**
 * Created by jorge on 27-12-2015.
 */
public class ThenSteps {

    @Then("^I should process the base features templates$")
    public void i_should_process_the_base_features_templates() throws Throwable {
        if (Hooks.testSetupNeeded) {
            FeaturesProcessor.processBaseFeatures(Hooks.getBasePagesData());
        }
    }

    @Then("^I should process the brands features templates$")
    public void i_should_process_the_brands_features_templates() throws Throwable {
        if (Hooks.testSetupNeeded) {
            FeaturesProcessor.processBrandFeatures(Hooks.testBrandPagesData);
        }
    }

    @Then("^I should process the items features templates$")
    public void i_should_process_the_items_features_templates() throws Throwable {
        if (Hooks.testSetupNeeded) {
            FeaturesProcessor.processItemsFeatures(Hooks.testBrandItemPagesData);
        }
    }

}
