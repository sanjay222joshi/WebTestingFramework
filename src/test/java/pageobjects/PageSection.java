package pageobjects;

import helpers.SeleniumFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Created by jorge on 11-01-2016.
 */
public abstract class PageSection {

    private final SearchContext context;
    private final String selector;


    public PageSection(final SearchContext context,
                       final String selector) {
        this.context = context;
        this.selector = selector;
    }

    public abstract boolean hasSection();
    public abstract WebElement getSection();

    public SearchContext getContext() {
        return context;
    }

    public String getSelector() {
        return selector;
    }

}
