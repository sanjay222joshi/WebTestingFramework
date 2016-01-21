package com.famous_smoke.automation.step_definitions.setup;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.helpers.FeaturesProcessor;
import cucumber.api.java.en.Then;
import java.util.ArrayList;

/**
 * Created by jorge on 27-12-2015.
 */
public class ThenSteps {

    @Then("^I should process the features$")
    public void i_should_process_the_features() throws Throwable {
        if (Hooks.testSetupNeeded) {
            FeaturesProcessor.processFeatures(new ArrayList<>(Hooks.testBrandPagesData));
        }
    }

}
