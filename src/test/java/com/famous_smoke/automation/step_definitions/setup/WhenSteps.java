package com.famous_smoke.automation.step_definitions.setup;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.data.DataWorkbook;
import cucumber.api.java.en.When;

/**
 * Created by jorge on 27-12-2015.
 */
public class WhenSteps {

    @When("^I store the extracted data$")
    public void i_store_the_extracted_data() throws Throwable {
        if (Hooks.testSetupNeeded) {
            DataWorkbook workbook = DataWorkbook.getTestDataWorkbook();
            workbook.writeBrandPages(Hooks.testBrandPagesData);
        }
    }

}
