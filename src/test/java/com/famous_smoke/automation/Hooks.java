package com.famous_smoke.automation;

import com.famous_smoke.automation.data.BrandPageData;
import com.famous_smoke.automation.data.DataWorkbook;
import com.famous_smoke.automation.navigation.Navigator;
import com.famous_smoke.automation.navigation.WebDriverFactory;
import com.famous_smoke.automation.util.TestConfigReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hooks {

    public static final Map<String, BrandPageData> TEST_DATA_MAP = createTestDataMap();

    public static String testUrl;
    public static Integer testMaximumCrawls;
    public static BrandPageData extractedBrandPageData;
    public static BrandPageData testBrandPageData;
    public static Collection<BrandPageData> testBrandPagesData;
    public static boolean testSetupNeeded;
    private static boolean shutDownSet = false;

    @Before
    public void shutOffLogger() throws Throwable {
        LogFactory.getFactory().setAttribute(
                "org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog"
        );
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
    }

    @Before
    public void createDriver() {
        if (Navigator.driver == null) {
            Navigator.driver = WebDriverFactory.createDriver(TestConfigReader.getSeleniumDriver());
            Navigator.driver.manage().window().maximize();
        }
    }

    @Before
    public void setUpShutdownHook() {
        if (!shutDownSet) {
            Runtime.getRuntime().addShutdownHook(new Thread(Navigator::closeNavigator));
            shutDownSet = true;
        }
    }

    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    @After
    public void checkForFailure(final Scenario scenario) {
        if (scenario.isFailed()) {
            embedScreenshot(scenario, Navigator.driver);
        }
    }

    private void embedScreenshot(final Scenario scenario,
                                 final WebDriver driver) {
        try {
            if (Navigator.driver.getClass().isAssignableFrom(TakesScreenshot.class)) {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                scenario.embed(takeScreenshot(driver), "image/png");
            }
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

    private static byte[] takeScreenshot(final WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }


    private static Map<String, BrandPageData> createTestDataMap() {
        try {
            Collection<BrandPageData> brands = DataWorkbook.getDefaultWorkbook().readBrands();
            ConcurrentHashMap<String, BrandPageData> map = new ConcurrentHashMap<>();
            brands.stream().forEach(brand -> map.putIfAbsent(brand.getURL(), brand));
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}