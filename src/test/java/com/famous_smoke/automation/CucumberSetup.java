package com.famous_smoke.automation;

import com.famous_smoke.automation.factory.DriverFactory;
import com.famous_smoke.automation.helpers.Navigator;
import com.famous_smoke.automation.helpers.TestConfigReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jorge on 16-01-2016.
 */
public class CucumberSetup {

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
        Navigator.driver = DriverFactory.createDriver(TestConfigReader.getSeleniumDriver());
        Navigator.driver.manage().window().maximize();
    }

    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    @After
    public void cleanUp(final Scenario scenario) {
        if (scenario.isFailed()) {
            embedScreenshot(scenario, Navigator.driver);
        }
        Navigator.driver.quit();
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
}
