package com.famous_smoke.automation.navigation;

/**
 * <p>The WebDrivers supported by the framework.</p>
 *
 * <p>These values are the ones that can be used in the
 * <strong>TestConfig.properties</strong> file to define
 * which webdriver will be used to run the tests.</p>
 */
public enum SupportedWebDrivers {
    LOCAL_CHROME,
    LOCAL_FIREFOX,
    LOCAL_HTMLUNIT,
    REMOTE_CHROME,
    REMOTE_FIREFOX;
}
