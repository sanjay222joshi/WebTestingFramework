import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pageobjects.BrandCategoriesPage;
import pageobjects.BrandPage;

import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 21-12-2015.
 */
public class Main {

    public static void main(String[] args) {
        Instant start = Instant.now();
        WebDriver driver = new HtmlUnitDriver();
        driver.get("https://www.famous-smoke.com/brand-list");
        BrandCategoriesPage page = new BrandCategoriesPage(driver);
        page.initializeElements();
        List<String> links = page.getBrandsLinks();
        List<String> pages = new ArrayList<>();
        System.out.println(links.size());
        for (String link : links) {
            System.out.println(link);
            pages.add(navigate(link));
        }
        System.out.println(links.size());
        System.out.println(pages.size());
        Instant end = Instant.now();
        long minutes = ChronoUnit.MINUTES.between(start, end);
        System.out.println(minutes);
    }

    private static String navigate(String url) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get(url);
        BrandPage page = new BrandPage(driver);
        page.initializeElements();

        StringBuilder builder = new StringBuilder();
        builder.append("Url: " + page.getURL());
        builder.append("\n");
        builder.append("Title: " + page.getTitle());
        builder.append("\n");
        builder.append("MetaDescription: " + page.getMetaDescription());
        builder.append("\n");
        builder.append("H1: " + page.getHeader1());
        builder.append("\n");
        builder.append("Description: " + page.getDescription());
        builder.append("\n");
        builder.append("BreadCrumbs: ");
        builder.append(page.getBreadCrumbsText());
        builder.append("\n");
        for (String breadcrumb : page.getBreadCrumbs()) {
            builder.append(breadcrumb);
            builder.append("\n");
        }
        return builder.toString();
    }
}
