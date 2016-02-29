package com.famous_smoke.automation.util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Provides a group of methods to inspect a Selenium
 * SearchContext for objects based on CSS, XPATH or Name.</p>
 */
public class SeleniumFinder {

    private SeleniumFinder() {
        //not called
    }

    /**
     * Queries a SearchContext for a single WebElement
     * using the cssSelector.
     * @param context the SearchContext where we
     *                are going to search the WebElement.
     * @param css the CSS pattern we are going to
     *            search.
     * @return the WebElement that conforms to the search.
     */
    public static WebElement findElementByCss(final SearchContext context,
                                              final String css) {
        return findElement(context, By.cssSelector(css));
    }

    /**
     * Queries a SearchContext for a List of WebElements
     * using the cssSelector.
     * @param context the SearchContext where we
     *                are going to search the WebElements.
     * @param css the CSS pattern we are going to
     *            search.
     * @return the WebElements that conforms to the search.
     */
    public static List<WebElement> findElementsByCss(final SearchContext context,
                                                     final String css) {
        return findElements(context, By.cssSelector(css));
    }

    /**
     * Queries a SearchContext for a single WebElement
     * using the name.
     * @param context the SearchContext where we
     *                are going to search the WebElement.
     * @param name the name of the object we are going to
     *             search.
     * @return the WebElement that conforms to the search.
     */
    public static WebElement findElementByName(final SearchContext context,
                                               final String name) {
        return findElement(context, By.name(name));
    }

    /**
     * Queries a SearchContext for a List of WebElements
     * using the name.
     * @param context the SearchContext where we
     *                are going to search the WebElements.
     * @param name the name of the object we are going to
     *             search.
     * @return the WebElements that conforms to the search.
     */
    public static List<WebElement> findElementsByName(final SearchContext context,
                                                      final String name) {
        return findElements(context, By.name(name));
    }

    /**
     * Queries a SearchContext for a single WebElement
     * using the XPATH.
     * @param context the SearchContext where we
     *                are going to search the WebElement.
     * @param xpath the XPATH route of the object we
     *              are going to search.
     * @return the WebElement that conforms to the search.
     */
    public static WebElement findElementByXPath(final SearchContext context,
                                                final String xpath) {
        return findElement(context, By.xpath(xpath));
    }

    /**
     * Queries a SearchContext for a List of WebElements
     * using the XPATH.
     * @param context the SearchContext where we
     *                are going to search the WebElements.
     * @param xpath the XPATH route of the object we are
     *              going to search.
     * @return the WebElements that conforms to the search.
     */
    public static List<WebElement> findElementsByXPath(final SearchContext context,
                                                       final String xpath) {
        return findElements(context, By.xpath(xpath));
    }

    /**
     * Queries the SearchContext using the By filter for
     * a single WebElement.
     * @param context the SearchContext.
     * @param by the By filter.
     * @return the WebElement that conforms to the search.
     */
    private static WebElement findElement(final SearchContext context,
                                          final By by){
        return context.findElement(by);
    }

    /**
     * Queries the SearchContext using the By filter for
     * a List of WebElements.
     * @param context the SearchContext.
     * @param by the By filter.
     * @return the WebElements that conforms to the search.
     */
    private static List<WebElement> findElements(final SearchContext context,
                                                 final By by){
        try {
            return context.findElements(by);
        } catch(NoSuchElementException exception) {
            return new ArrayList<>();
        }
    }
    
}
