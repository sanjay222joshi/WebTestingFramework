package com.famous_smoke.automation.factory;

import com.famous_smoke.automation.api.SupportedWebDrivers;
import com.famous_smoke.automation.helpers.TestConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jorge on 24-12-2015.
 */
public final class DriverFactory {

    private DriverFactory() {
        // not called
    }

    public static WebDriver createDriver(final SupportedWebDrivers driver) {
        switch (driver) {
            case LOCAL_CHROME: return createLocalChromeDriver();
            case LOCAL_FIREFOX: return createLocalFirefoxDriver();
            case LOCAL_HTMLUNIT: return createLocalHTMLUnitDriver();
            case REMOTE_CHROME: return createRemoteChromeDriver();
            case REMOTE_FIREFOX: return createRemoteFirefoxDriver();
            default: return createLocalHTMLUnitDriver();
        }
    }

    private static WebDriver createLocalHTMLUnitDriver() {
        return new HtmlUnitDriver();
    }

    private static WebDriver createLocalChromeDriver() {
        return new ChromeDriver();
    }

    private static WebDriver createLocalFirefoxDriver() {
        return new FirefoxDriver();
    }

    public static WebDriver createRemoteChromeDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        return createRemoteDriver(capabilities);
    }

    public static WebDriver createRemoteFirefoxDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        return createRemoteDriver(capabilities);
    }

    private static WebDriver createRemoteDriver(final DesiredCapabilities capabilities) {
        URL seleniumServerUrl = null;
        try {
             seleniumServerUrl = new URL("http://"
                    + TestConfigReader.getSeleniumServerHost()
                    + ":"
                    + TestConfigReader.getSeleniumServerPort()
                    + "/wd/hub"
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return new RemoteWebDriver(seleniumServerUrl, capabilities);
    }

}
