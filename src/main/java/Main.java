import api.Brand;
import helpers.DataWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pageobjects.BrandCategoriesPage;
import pageobjects.BrandPage;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jorge on 21-12-2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Instant start = Instant.now();
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://www.famous-smoke.com/brand-list");
        BrandCategoriesPage page = new BrandCategoriesPage(driver);
        page.initializeElements();
        List<String> links = page.getBrandsLinks();
        List<BrandPage> pages = new ArrayList<>();
        for (String link : links) {
            pages.add(navigate(link));
        }
        DataWorkbook workbook = DataWorkbook.getDefaultWorkbook();
        workbook.writeBrandPages(pages);
        Collection<Brand> brands = workbook.readBrands();
        System.out.println(pages.size());
        System.out.println(brands.size());
        Instant end = Instant.now();
        long minutes = ChronoUnit.MINUTES.between(start, end);
        System.out.println(minutes);
    }

    private static BrandPage navigate(String url) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get(url);
        BrandPage page = new BrandPage(driver);
        page.initializeElements();
        return page;
    }
}
