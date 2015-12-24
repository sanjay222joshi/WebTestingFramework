import api.BrandPageData;
import api.CategoriesPageData;
import helpers.DataWorkbook;
import helpers.WebDriverFactory;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pageobjects.CategoriesPage;
import pageobjects.BrandPage;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jorge on 21-12-2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Instant start = Instant.now();

        LogFactory.
                getFactory().
                setAttribute("org.apache.commons.logging.Log",
                        "org.apache.commons.logging.impl.NoOpLog");

        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

        WebDriver driver = WebDriverFactory.createSilentDriver(
                "https://www.famous-smoke.com/brand-list"
        );
        CategoriesPage page = new CategoriesPage(driver);
        page.initializeElements();
        CategoriesPageData categoriesData = page.getCategoriesData();
        List<BrandPageData> brandsData = new ArrayList<>();
        int i = 0;
        try {
            for (String link : categoriesData.getBrandsLinks()) {
                System.out.println(link);
                brandsData.add(navigate(link));
                ++i;
                if (i > 3)
                    break;
            }
        } catch (Exception ex) {
            System.err.println(i);
            ex.printStackTrace();
            System.exit(1);
        }

        DataWorkbook workbook = DataWorkbook.getDefaultWorkbook();
        workbook.writeBrandPages(brandsData);
        Collection<BrandPageData> brands = workbook.readBrands();
        System.out.println(categoriesData.getBrandsLinks().size());
        System.out.println(brands.size());
        Instant end = Instant.now();
        long minutes = ChronoUnit.MINUTES.between(start, end);
        System.out.println(minutes);
    }

    private static BrandPageData navigate(final String url) {
        WebDriver driver = WebDriverFactory.createSilentDriver(url);
        BrandPage page = new BrandPage(driver);
        page.initializeElements();
        return page.getBrandData();
    }
}
