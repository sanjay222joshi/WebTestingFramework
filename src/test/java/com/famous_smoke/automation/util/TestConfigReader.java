package com.famous_smoke.automation.util;

import com.famous_smoke.automation.navigation.SupportedWebDrivers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Reads the information of the Configuration file
 * we have defined for the Framework.</p>
 */
public class TestConfigReader {

    /**
     * The PATH of the Configuration file.
     */
    private static final String TESTCONFIG_FILE_PATH = "src/test/resources/properties/TestConfig.properties";
    /**
     * A Properties object containing all
     * the values inside the configuration
     * file.
     */
    private static final Properties TESTCONFIG_PROPERTIES = loadProperties();
    /**
     * The TestData Workbook Property name.
     */
    private static final String TESTDATA_WORKBOOK_PATH_PROPERTY = "TESTDATA_WORKBOOK_PATH";
    /**
     * The Maximum Crawls Property name.
     */
    private static final String MAXIMUM_CRAWLS_PROPERTY = "MAXIMUM_CRAWLS";
    /**
     * The Selenium Driver Property name.
     */
    private static final String SELENIUM_DRIVER_PROPERTY = "SELENIUM_DRIVER";
    /**
     * The Selenium Server Host Property name.
     */
    private static final String SELENIUM_SERVER_HOST_PROPERTY = "SELENIUM_SERVER_HOST";
    /**
     * The Selenium Server Port Property name.
     */
    private static final String SELENIUM_SERVER_PORT_PROPERTY = "SELENIUM_SERVER_PORT";

    /**
     * Evaluates the properties and determines
     * the TestData Workbook Path configured.
     * @return the String value with the TestData
     * Workbook Path.
     */
    public static String getTestDataWorkbookPath() {
        return TESTCONFIG_PROPERTIES.getProperty(TESTDATA_WORKBOOK_PATH_PROPERTY);
    }

    /**
     * Evaluates the properties and determines
     * the Maximum Crawls configured.
     * @return the Integer value of Maximum Crawls.
     */
    public static Integer getMaximumCrawls() {
        return Integer.valueOf(TESTCONFIG_PROPERTIES.getProperty(MAXIMUM_CRAWLS_PROPERTY));
    }

    /**
     * Evaluates the properties and determines
     * the Selenium Driver configured.
     * @return the SupportedWebDrivers enumerator.
     */
    public static SupportedWebDrivers getSeleniumDriver() {
        return SupportedWebDrivers.valueOf(TESTCONFIG_PROPERTIES.getProperty(SELENIUM_DRIVER_PROPERTY));
    }

    /**
     * Evaluates the properties and determines
     * the Selenium Host configured.
     * @return the Selenium Host name String.
     */
    public static String getSeleniumServerHost() {
        return TESTCONFIG_PROPERTIES.getProperty(SELENIUM_SERVER_HOST_PROPERTY);
    }

    /**
     * Evaluates the properties and determines
     * the Selenium Port configured.
     * @return the Selenium Port Integer value.
     */
    public static Integer getSeleniumServerPort() {
        return Integer.valueOf(TESTCONFIG_PROPERTIES.getProperty(SELENIUM_SERVER_PORT_PROPERTY));
    }

    /**
     * Loads the properties file in a Properties
     * object.
     * @return the Properties object.
     */
    private static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(TESTCONFIG_FILE_PATH);
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
