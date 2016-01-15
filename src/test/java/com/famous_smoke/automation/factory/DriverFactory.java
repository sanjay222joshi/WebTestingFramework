package com.famous_smoke.automation.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by jorge on 24-12-2015.
 */
public final class DriverFactory {

    private DriverFactory() {
        // not called
    }

    public static WebDriver createDefaultDriver() {
        return createLocalChromeDriver();
    }

    public static WebDriver createSilentDriver() {
        return new HtmlUnitDriver();
    }

    private static WebDriver createLocalChromeDriver() {
        return new ChromeDriver();
    }

    private static DesiredCapabilities getChromeCapabilities() {
        return DesiredCapabilities.chrome();
    }

}
