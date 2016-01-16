package com.famous_smoke.automation;

import com.famous_smoke.automation.api.BrandPageData;
import com.famous_smoke.automation.factory.DriverFactory;
import com.famous_smoke.automation.helpers.DataWorkbook;
import com.famous_smoke.automation.helpers.Navigator;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hooks {

    public static final Map<String, BrandPageData> TEST_DATA_MAP = createTestDataMap();

    public static String testUrl;
    public static Integer testMaximumCrawls;
    public static BrandPageData extractedBrandPageData;
    public static BrandPageData testBrandPageData;
    public static List<BrandPageData> testBrandPagesData;


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
}