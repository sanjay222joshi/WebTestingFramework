package com.famous_smoke.automation.helpers;

import com.famous_smoke.automation.factory.DriverFactory;
import com.famous_smoke.automation.pageobjects.BrandPage;
import com.famous_smoke.automation.pageobjects.CategoriesPage;
import com.famous_smoke.automation.pageobjects.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jorge on 14-01-2016.
 */
public class Navigator {

    public static WebDriver driver;

    public static void goUrl(final String url) {
        driver.get(url);
    }

    public static void goBack(){
        driver.navigate().back();
    }

    public static void initializePages() {
        Navigator.initializePage(CategoriesPage.class);
        Navigator.initializePage(BrandPage.class);
    }

    private static void initializePage(final Class<? extends PageObject> page){
        PageFactory.initElements(driver, page);
    }

    public static String getPageSource() {
        return driver.getPageSource();
    }

}
