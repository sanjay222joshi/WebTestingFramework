package factory;

import org.openqa.selenium.SearchContext;
import pageobjects.HeaderSection;

/**
 * Created by jorge on 11-01-2016.
 */
public class SectionFactory {
    private SectionFactory() {
        //not called
    }

    public static HeaderSection createHeader(final SearchContext context,
                                             final String selector) {
        return new HeaderSection(context, selector);
    }
}
