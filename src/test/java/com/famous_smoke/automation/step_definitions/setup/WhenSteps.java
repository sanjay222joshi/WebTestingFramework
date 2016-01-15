package com.famous_smoke.automation.step_definitions.setup;

import com.famous_smoke.automation.Hooks;
import com.famous_smoke.automation.helpers.DataWorkbook;
import cucumber.api.java.en.When;

/**
 * Created by jorge on 27-12-2015.
 */
public class WhenSteps {

    @When("^I store the extracted data$")
    public void i_store_the_extracted_data() throws Throwable {
        DataWorkbook workbook = DataWorkbook.getDefaultWorkbook();
        workbook.writeBrandPages(Hooks.testBrandPagesData);
    }

}
