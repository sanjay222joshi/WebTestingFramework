package step_definitions;

import api.SeleniumAction;
import modules.CrawlerAction;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features",
		plugin = {"pretty", "html:target/cucumber-html-report","json:cucumber.json"},
		tags = {}
		)
public class RunCukesTest{

	@BeforeClass
    public static void loadTestData() throws Throwable{
        SeleniumAction<Integer> crawler = new CrawlerAction();
        System.out.println("Starting the crawler...");
        int i = crawler.execute(Hooks.BRAND_LIST_URL);
        System.out.println(i + " Brands written in the TestData File.");
    }
}