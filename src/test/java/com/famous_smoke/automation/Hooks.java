package com.famous_smoke.automation;

import com.famous_smoke.automation.data.*;
import com.famous_smoke.automation.navigation.Navigator;
import com.famous_smoke.automation.navigation.WebDriverFactory;
import com.famous_smoke.automation.pageobjects.BasePage;
import com.famous_smoke.automation.util.TestConfigReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>These are the Hooks for the tests, that is, the common objects that
 * we use to validate the tests results and the methods to be executed before
 * and after the tests</p>
 *
 * <p>The objects to be used in the tests are </p>
 */
public class Hooks {

    /**
     * This constant map contains the BrandPageData that was extracted by the setup features.
     * It's identified by the url to which the data was extracted.
     */
    public static final Map<String, BrandPageData>     TEST_BRAND_DATA_MAP = createTestBrandDataMap();
    public static final Map<String, BrandItemPageData> TEST_ITEM_DATA_MAP  = createTestBrandItemDataMap();
    public static final Map<String, BasePageData>      TEST_BASE_DATA_MAP  = createTestBaseDataMap();

    /**
     * The starting URL of each test.
     */
    public static String testUrl;
    /**
     * The maximum crawls for the brand crawler.
     */
    public static Integer testMaximumCrawls;

    public static BasePageData testBasePageData;
    public static BasePageData extractedBasePageData;
    /**
     * This is the BrandPageData of the URL of
     * any test as it is represented in the Map constant.
     */
    public static BrandPageData extractedBrandPageData;
    /**
     * Contains the Brand List Page Data
     */
    public static BrandListPageData testBrandListPageData;
    /**
     * The BrandPageData that is fetched by the test
     * from the URL at runtime. This is used by tests
     * that run against only one URL
     */
    public static BrandPageData testBrandPageData;
    /**
     * All the BrandPageData objects that are fetched by tests
     * that need to collect data from multiple URLS.
     */
    public static Collection<BrandPageData> testBrandPagesData;
    /**
     * All the BrandPageItemData objects that are fetched by tests
     * that need to collect data from multiple urls.
     */
    public static Collection<BrandItemPageData> testBrandItemPagesData;

    public static BrandItemPageData testBrandItemPageData;
    public static BrandItemPageData extractedBrandItemPageData;
    /**
     * This variable is for the check to see if the features
     * templates need to be processed.
     */
    public static boolean testSetupNeeded = true;
    /**
     * This variable determines if the shutdown hook has already
     * been set.
     */
    private static boolean shutDownSet = false;

    public static Collection<BasePageData> getBasePagesData() {
        try {
            ArrayList<BasePageData> data = new ArrayList<>();
            data.addAll(DataWorkbook.getTestDataWorkbook().readBrandPages());
            data.addAll(DataWorkbook.getTestDataWorkbook().readBrandItemPages());
            return data;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * This operation shutoffs the logging used by
     * the HTMLUnit webdriver. It reduces the verbosity
     * of the tests.
     * @throws Throwable
     */
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
     * If the Navigator webdriver is not set, we create
     * a new webdriver according to the configuration read
     * in the properties file.
     */
    @Before
    public void createDriver() {
        if (Navigator.driver == null) {
            Navigator.driver = WebDriverFactory.createDriver(TestConfigReader.getSeleniumDriver());
            Navigator.driver.manage().window().maximize();
        }
    }

    /**
     * If the shutdown hook is not set, we create it
     * as the Navigator.closeNavigator method.
     *
     * The shutdown hook is an operation that is run
     * when the JVM is shutting down a process, so
     * this will be run only once for each Test runner.
     */
    @Before
    public void setUpShutdownHook() {
        if (!shutDownSet) {
            Runtime.getRuntime().addShutdownHook(new Thread(Navigator::closeNavigator));
            shutDownSet = true;
        }
    }

    /**
     * Embed a screenshot in test report if test is marked
     * as failed.
     */
    @After
    public void checkForFailure(final Scenario scenario) {
        if (scenario.isFailed()) {
            embedScreenshot(scenario, Navigator.driver);
        }
    }

    /**
     * We set up all the static values to null
     * so that each test can run without inheriting
     * values from previous tests.
     */
    @After
    public void cleanUp() {
        testUrl = null;
        testMaximumCrawls = null;
        extractedBrandPageData = null;
        testBrandPageData = null;
        testBrandPagesData = null;
    }

    /**
     * This method creates the Map constant.
     *
     * It reads all the brands in the XLS Workbook
     * and stores them in a collection that it then
     * iterates store it's values inside of map.
     * @return the ConcurrentHashMap with all scrapped data.
     * @throws RuntimeException if anything goes wrong
     * with the IO.
     */
    private static Map<String, BrandPageData> createTestBrandDataMap() {
        try {
            Collection<BrandPageData> brands = DataWorkbook.getTestDataWorkbook().readBrandPages();
            ConcurrentHashMap<String, BrandPageData> map = new ConcurrentHashMap<>();
            brands.stream().forEach(brand -> map.putIfAbsent(brand.getURL(), brand));
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, BrandItemPageData> createTestBrandItemDataMap() {
        try {
            Collection<BrandItemPageData> items = DataWorkbook.getTestDataWorkbook().readBrandItemPages();
            ConcurrentHashMap<String, BrandItemPageData> map = new ConcurrentHashMap<>();
            items.stream().forEach(item -> map.putIfAbsent(item.getURL(), item));
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, BasePageData> createTestBaseDataMap() {
        ConcurrentHashMap<String, BasePageData> map = new ConcurrentHashMap<>();
        getBasePagesData().stream().forEach(data -> map.putIfAbsent(data.getURL(), data));
        return map;
    }

    /**
     * This method evaluates the class of the current
     * Webdriver to determine if it can be used to
     * take a screenshot.
     *
     * If it is assignable, it takes an snapshot and loads
     * it unto the scenario.
     * @param scenario the scenario that wants to be added
     *                 a screenshot
     * @param driver the selenium webdriver.
     */
    private void embedScreenshot(final Scenario scenario,
                                 final WebDriver driver) {
        try {
            if (Navigator.driver instanceof TakesScreenshot) {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                scenario.embed(takeScreenshot(driver), "image/png");
                saveScreenshot(new AShot().takeScreenshot(driver), scenario.getName());
            }
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

    /**
     * This method casts the webdriver to the
     * TakesScreenshot interface and gets the
     * screenshot as a byte array.
     * @param driver the selenium webdriver
     * @return the byte array screenshot.
     */
    private static byte[] takeScreenshot(final WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    private static void saveScreenshot(final Screenshot screenshot,
                                       final String fileName) {
        try {
            Files.createDirectory(Paths.get("target/screenshots/"));
            File outputFile = Files.createFile(
                    Paths.get("target/screenshots/"+fileName+"-"+ Instant.now().toEpochMilli()+".png")
            ).toFile();
            ImageIO.write(
                    screenshot.getImage(),
                    "png",
                    outputFile
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}