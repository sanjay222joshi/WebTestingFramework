package com.famous_smoke.automation.navigation;

import com.famous_smoke.automation.pageobjects.BasePage;
import com.famous_smoke.automation.pageobjects.BrandPage;
import com.famous_smoke.automation.pageobjects.CategoriesPage;
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

    public static String getPageSource() {
        return driver.getPageSource();
    }

    public static void closeNavigator() {
        driver.quit();
        driver = null;
    }

    private static void initializePage(final Class<? extends BasePage> page){
        PageFactory.initElements(driver, page);
    }


}
