package validators;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by jorge on 07-01-2016.
 */
public class SeleniumValidators {

    private SeleniumValidators() {
        //not called
    }

    public static WebElement findElementByCss(final SearchContext context,
                                                 final String css) {
        return findElement(context, By.cssSelector(css));
    }

    public static List<WebElement> findElementsByCss(final SearchContext context,
                                                        final String css) {
        return findElements(context, By.cssSelector(css));
    }

    public static WebElement findElementByName(final SearchContext context,
                                                  final String name) {
        return findElement(context, By.name(name));
    }

    public static List<WebElement> findElementsByName(final SearchContext context,
                                                         final String name) {
        return findElements(context, By.name(name));
    }

    public static WebElement findElementByXpath(final SearchContext context,
                                                   final String xpath) {
        return findElement(context, By.xpath(xpath));
    }

    public static List<WebElement> findElementsByXpath(final SearchContext context,
                                                          final String xpath) {
        return findElements(context, By.xpath(xpath));
    }

    private static WebElement findElement(final SearchContext context,
                                          final By by){
        return context.findElement(by);
    }

    private static List<WebElement> findElements(final SearchContext context,
                                                 final By by){
        return context.findElements(by);
    }
    
}
