package step_definitions;

import api.BrandPageData;
import api.SeleniumAction;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.DataWorkbook;
import helpers.FeaturesProcessor;
import modules.CrawlerAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 27-12-2015.
 */
public class SetupDataSteps {

    private SeleniumAction<List<BrandPageData>> crawler;
    private List<BrandPageData> data;
    private boolean check;

    @Given("^I need cucumber to Setup the Urls and TestData$")
    public void i_need_cucumber_to_Setup_the_Urls_and_TestData() throws Throwable {
        check = FeaturesProcessor.needToProcess();
        if (check) {
            crawler = new CrawlerAction();
        }
    }

    @When("^I first run the test$")
    public void i_first_run_the_test() throws Throwable {
        if (check) {
            data = crawler.execute(Hooks.BRAND_LIST_URL);
        }
    }

    @Then("^I should process the features$")
    public void i_should_process_the_features() throws Throwable {
        if (check) {
            DataWorkbook workbook = DataWorkbook.getDefaultWorkbook();
            workbook.writeBrandPages(data);
            FeaturesProcessor.processFeatures(new ArrayList<>(data));
        }
    }
}
