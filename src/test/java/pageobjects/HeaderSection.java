package pageobjects;

import helpers.SeleniumFinder;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;

import static helpers.SeleniumFinder.findElementByCss;
import static helpers.SeleniumFinder.findElementsByCss;
import static helpers.SeleniumFinder.findElementsByXpath;
import static pageobjects.HeaderConstants.*;

/**
 * Created by jorge on 11-01-2016.
 */
public class HeaderSection extends PageSection {


    public HeaderSection(final SearchContext context,
                         final String selector) {
        super(context, selector);
    }

    @Override
    public boolean hasSection() {
        return !findElementsByCss(
                getContext(),
                getSelector()
        ).isEmpty();
    }

    @Override
    public WebElement getSection() {
        if (hasSection()) {
            return findElementByCss(getContext(),getSelector());
        }
        return null;
    }

    public boolean hasTopLogo() {
        return !findElementsByCss(
                getContext(),
                TOP_LOGO_CSS
        ).isEmpty();
    }

    public WebElement getTopLogo() {
        if (hasSection()) {
            return findElementByCss(
                    getContext(),
                    TOP_LOGO_CSS);
        }
        return null;
    }

    public WebElement getTopLogoImg() {
        if (getTopLogo() != null) {
            return findElementByCss(
                    getTopLogo(),
                    TOP_LOGO_IMG_CSS);
        }
        return null;
    }

    public boolean hasSiteNavLinks() {
        return !findElementsByXpath(
                findElementByCss(getContext(), SITENAV_CSS),
                SITENAV_LINKS_XPATH
        ).isEmpty();
    }

    public Collection<WebElement> getSiteNavLinks() {
        return new ArrayList<>(
                findElementsByXpath(
                        findElementByCss(getContext(), SITENAV_CSS),
                        SITENAV_LINKS_XPATH
                )
        );
    }
}
