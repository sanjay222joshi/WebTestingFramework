package step_definitions;


import api.BrandPageData;
import helpers.DataWorkbook;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hooks{
    public static final String BRAND_LIST_URL  = "https://www.famous-smoke.com/brand-list";
    public static WebDriver driver;

    @Before
    public void shutOffLogger() throws Throwable {
        LogFactory.getFactory().setAttribute(
                "org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog"
        );
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
    }

     
    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {

        if(scenario.isFailed()) {
        try {
            //scenario.write("Current Page URL is " + driver.getCurrentUrl());
            //byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            //scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }

        }
        //driver.quit();
    }

    public static Map<String, BrandPageData> createTestDataMap() throws IOException {
        Collection<BrandPageData> brands = DataWorkbook.getDefaultWorkbook().readBrands();
        ConcurrentHashMap<String, BrandPageData> map = new ConcurrentHashMap<>();
        brands.stream().forEach(brand -> map.putIfAbsent(brand.getURL(), brand));
        return map;
    }
    
}