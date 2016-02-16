package com.famous_smoke.automation.navigation;

import com.famous_smoke.automation.pageobjects.BasePage;
import com.famous_smoke.automation.pageobjects.BrandItemPage;
import com.famous_smoke.automation.pageobjects.BrandPage;
import com.famous_smoke.automation.pageobjects.CategoriesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * <p>Handles the navigation of the Framework passing orders
 * to the Selenium WebDriver.</p>
 */
public class Navigator {

    /**
     * The WebDriver that is instantiated in each
     * test run.
     */
    public static WebDriver driver;

    /**
     * Navigates to the specified URL
     * @param url the URL which
     *            we are going to get.
     */
    public static void goUrl(final String url) {
        driver.get(url);
    }

    /**
     * Moves the WebDriver to the
     * previous page.
     */
    public static void goBack(){
        driver.navigate().back();
    }

    /**
     * Initializes the WebElements
     * of the Selenium PageObjects
     * we have defined in the
     * pageobjects package.
     */
    public static void initializePages() {
        Navigator.initializePage(CategoriesPage.class);
        Navigator.initializePage(BrandPage.class);
        Navigator.initializePage(BrandItemPage.class);
    }

    /**
     * Retrieves the HTML source of the
     * current WebDriver location.
     * @return the HTML Source.
     */
    public static String getPageSource() {
        return driver.getPageSource();
    }

    /**
     * Closes and nullifies the
     * current WebDriver.
     */
    public static void closeNavigator() {
        driver.quit();
        driver = null;
    }

    /**
     * Initializes the WebElements of a
     * PageObject Class that inherits
     * from the BasePage class.
     * @param page the BasePage class.
     */
    private static void initializePage(final Class<? extends BasePage> page){
        PageFactory.initElements(driver, page);
    }

}
