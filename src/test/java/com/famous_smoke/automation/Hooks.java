package com.famous_smoke.automation;

import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.helpers.DataWorkbook;
import com.famous_smoke.automation.pageobjects.BrandPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hooks {
    public static final String BRAND_LIST_URL  = "https://www.famous-smoke.com/brand-list";
    public static final Map<String, BrandPageData> TEST_DATA_MAP = createTestDataMap();

    public static String url;
    public static BrandPage testBrandPage;
    public static BrandPageData testBrandPageData;

    @Before
    public void shutOffLogger() throws Throwable {
        LogFactory.getFactory().setAttribute(
                "org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog"
        );
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
    }

    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    @After
    public void cleanUp(final Scenario scenario) {
        if (testBrandPage == null) {
            return;
        }
        if (scenario.isFailed()) {
            embedScreenshot(scenario, testBrandPage.getDriver());
        }
        quitDriver(testBrandPage.getDriver());
    }

    private void embedScreenshot(final Scenario scenario,
                                 final WebDriver driver) {
        try {
            if(driver.getClass().isAssignableFrom(HtmlUnitDriver.class)){
                throw new WebDriverException("HTMLUnitDriver doesn't support screenshot");
            }
            scenario.write("Current Page URL is " + driver.getCurrentUrl());
            scenario.embed(takeScreenshot(driver), "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

    private void quitDriver(final WebDriver driver) {
        driver.quit();
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

    private static byte[] takeScreenshot(final WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
    
}